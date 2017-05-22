package com.eltonkola.crud.controllers;

import com.eltonkola.crud.controllers.pager.PageWrapper;
import com.eltonkola.crud.domain.Burim;
import com.eltonkola.crud.domain.Song;
import com.eltonkola.crud.service.BurimeServiceInterface;
import com.eltonkola.crud.service.SongServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

/**
 * Created by elton on 4/7/17.
 */

@Controller
@EnableAutoConfiguration
@ComponentScan
public class AdminSongController {

    @Autowired
    SongServiceInterface mSongServiceInterface;

    @RequestMapping(value = {"/admin/songs"}, method = RequestMethod.GET)
    public String songs(Model model, Pageable pageable) {
        Page<Song> productPage = mSongServiceInterface.findAll(pageable);
        PageWrapper<Song> page = new PageWrapper<Song>(productPage, "/admin/songs");
        model.addAttribute("terekenget", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("total_songs", mSongServiceInterface.nrTotalSongs());

        return "admin/songs";

    }





    @RequestMapping(value = "/admin/songs/deleteall", method = RequestMethod.GET)
    public String deleteall(Model model) {
        mSongServiceInterface.deleteAllSongs();
        return "redirect:/admin/songs";
    }


    @RequestMapping(value = "/admin/songs/{operation}/{songId}/{page}/{size}", method = RequestMethod.GET)
    public String editDeleteSong(@PathVariable("operation") String operation,
                                        @PathVariable("songId") Long songId,
                                        @PathVariable("page") Long page,
                                        @PathVariable("size") Long size,
                                        final RedirectAttributes redirectAttributes,
                                        Model model) {
        if(operation.equals("delete")) {
            if(mSongServiceInterface.deleteSong(songId)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        }
        return "redirect:/admin/songs?page=" + page + "&size=" + size;
    }


}
