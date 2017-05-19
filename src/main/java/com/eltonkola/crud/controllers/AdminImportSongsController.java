package com.eltonkola.crud.controllers;

import com.eltonkola.crud.domain.ImportFileObject;
import com.eltonkola.crud.domain.Song;
import com.eltonkola.crud.service.SongServiceInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by elton on 4/7/17.
 */

@Controller
@EnableAutoConfiguration
@ComponentScan
public class AdminImportSongsController {

    private static final Logger log = LoggerFactory.getLogger(AdminImportSongsController.class);

    @Value("${app_export_folder_path}")
    private String appExportFolderPath;

    @Autowired
    SongServiceInterface mSongServiceInterface;

    @RequestMapping(value = {"/admin/importsongs"}, method = RequestMethod.GET)
    public String news(Model model) {

        File dir = new File(appExportFolderPath);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".json");
            }
        });

        List<ImportFileObject> mFiles =  new ArrayList<>();

        for(File f: files){
            mFiles.add(new ImportFileObject(f));
        }

        model.addAttribute("tereFilet", mFiles);
        return "admin/importsongs";
    }

    @RequestMapping(value = {"/admin/importsongs/delete/{path}"}, method = RequestMethod.GET)
    public String delete(final RedirectAttributes redirectAttributes, Model model, @PathVariable("path") String path) {

        try{
            File f = new File(appExportFolderPath + "/" + path);
            f.delete();
            redirectAttributes.addFlashAttribute("status","file deleted");
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("status","error deleting file:" + path + " error: " + e.getMessage());
        }

        return "admin/importsongs";
    }

    @RequestMapping(value = {"/admin/importsongs/import/{path}"}, method = RequestMethod.GET)
    public String importSongs(final RedirectAttributes redirectAttributes, Model model, @PathVariable("path") String path) {
        log.debug("import songs:" + path);
        //TODO - chnage this later, maybe with spring batch
        try {
            JsonReader jsonReader = new JsonReader(new FileReader(appExportFolderPath + "/" + path + ".json"));
            Type listType = new TypeToken<HashSet<String>>() {
            }.getType();
            HashSet<String> mSongList = new Gson().fromJson(jsonReader, listType);
            log.debug("songs to import:" + mSongList.size());

            for(String s: mSongList){
                log.debug("songs to import:" + mSongList.size());
                if(!mSongServiceInterface.songExistWithPath(s)){
                    mSongServiceInterface.saveSong(new Song(s));
                }
            }
            redirectAttributes.addFlashAttribute("status","imported them all");
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("status","error importing:" + e.getMessage());
        }


        return "admin/importsongs";
    }


}
