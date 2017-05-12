package com.eltonkola.crud.crawler;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import com.eltonkola.crud.config.WebSocketConfig;
import com.eltonkola.crud.domain.Song;
import com.eltonkola.crud.service.SongServiceInterface;
import com.eltonkola.crud.websocket.EchoWebSocketHandler;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import org.apache.http.Header;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketHandler;

/**
 * @author Yasser Ganjisaffar
 */
public class SongCrawler extends WebCrawler {

    private SongServiceInterface mSongServiceInterface;

    List<String> mSongs = new ArrayList<>();

    EchoWebSocketHandler mEchoWebSocketHandler;

    public SongCrawler(EchoWebSocketHandler echoWebSocketHandler, SongServiceInterface repository) {
        mSongServiceInterface =  repository;
        mEchoWebSocketHandler = echoWebSocketHandler;
    }

    @Override
    public void onBeforeExit(){

        Gson gson = new Gson();
        String json = gson.toJson(mSongs);
        try {
            FileWriter writer = new FileWriter("kenget.json");
            writer.write(json);
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }







    private static final Pattern filters = Pattern.compile(
            ".*(\\.(css|js|wav|avi|mov|mpeg|ram|m4v|pdf" +
                    "|rm|smil|wmv|swf|wma|zip|rar|gz|bmp|gif|jpe?g|png|tiff))$");

    private static final Pattern songPatterns = Pattern.compile(".*(\\.(mid|mp2|mp3|mp4?))$");

    private static File storageFolder;
    private static String[] crawlDomains;

    public static void configure(String[] domain, String storageFolderName) {
        crawlDomains = domain;

        storageFolder = new File(storageFolderName);
        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (filters.matcher(href).matches()) {
            return false;
        }

//        if (songPatterns.matcher(href).matches()) {
        if (href.endsWith(".mp3")) {
            mSongs.add(href);
            if(mEchoWebSocketHandler!=null) {
                mEchoWebSocketHandler.broadcast("found song: " + href);
            }
            if(mSongServiceInterface!=null){
                Song song = new Song();
                song.setUrl_song(href);
                song.setSong_title(url.getTag());
                song.setArtist_name(url.getDepth()+"");
                mSongServiceInterface.saveSong(song);
            }
            return false;
        }

//        for (String domain : crawlDomains) {
//            if (href.startsWith(domain)) {
//                return true;
//            }
//        }
//        return false;
        return href.contains("shkarko");
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        if(mEchoWebSocketHandler!=null) {
            mEchoWebSocketHandler.broadcast("visit: " + url);
        }
        logger.info("visist: " , url);
/*

//        if(mEchoWebSocketHandler!=null) {
            mEchoWebSocketHandler.broadcast("visit: " + url);
//        }
        // We are only interested in processing images which are bigger than 10k
        if (!songPatterns.matcher(url).matches() ||
                !((page.getParseData() instanceof BinaryParseData) ||
                        (page.getContentData().length < (10 * 1024)))) {
            return;
        }

        mSongs.add(url);

        // get a unique name for storing this image
        String extension = url.substring(url.lastIndexOf('.'));
        String hashedName = UUID.randomUUID() + extension;

        // store image
        String filename = storageFolder.getAbsolutePath() + "/" + hashedName;
        try {
            Files.write(page.getContentData(), new File(filename));
            logger.info("Stored: {}", url);
        } catch (IOException iox) {
            logger.error("Failed to write file: " + filename, iox);
        }
        */
    }



}