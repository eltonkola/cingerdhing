package com.eltonkola.crud.spider;


import com.eltonkola.crud.websocket.EchoWebSocketHandler;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class Mp3Spider {

    private HashSet<String> links;
    private HashSet<String> songs;

    private String url1;
    private String url2;
    private EchoWebSocketHandler mEchoWebSocketHandler;

    int MAX_LIMIT = -1;

    public Mp3Spider(String url, String secondaryDomain, EchoWebSocketHandler client) {
        links = new HashSet<String>();
        songs = new HashSet<String>();
        url1 = url;
        url2 = secondaryDomain;
        mEchoWebSocketHandler = client;
    }

    public void start(){
        getPageLinks(url1, true);
    }

    public void getPageLinks(String URL, boolean done) {
        //4. Check if you have already crawled the URLs
        //(we are intentionally not checking for duplicate content in this example)
        if (!links.contains(URL)) {
            try {
                //4. (i) If not add it to the index
                if (links.add(URL)) {
                    System.out.println("added:" + URL);
//                    if(mEchoWebSocketHandler!=null)
//                        mEchoWebSocketHandler.broadcast("New url:" + URL);
                }

                //2. Fetch the HTML code
                Document document = Jsoup.connect(URL)//URLEncoder.encode(URL, "UTF-8")
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")

                        .ignoreContentType(true)
                        .timeout(12000)
                        .followRedirects(true)

                        .get();
                //3. Parse the HTML to extract links to other URLs
                Elements linksOnPage = document.select("a[href]");

                //5. For each extracted URL... go back to Step 4.
                for (Element page : linksOnPage) {
                    String link = cleanUrl(page.attr("abs:href"));
                    if(link.endsWith(".mp3")){
                        System.out.println("found song:" + link);
                        if(!songs.contains(link)) {
                            songs.add(link);
                            if(mEchoWebSocketHandler!=null)
                            mEchoWebSocketHandler.broadcast("Found song:" + link);
                        }
                    }else if( (link.contains(url1) || link.contains(url2) ) && (MAX_LIMIT <0 || songs.size()< MAX_LIMIT)){
                        getPageLinks(cleanUrl(link), false);
                    }
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
                e.printStackTrace();
            }
        }

        if(done){
            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.err.println("!!!Finished, pages:" + links.size() + " songs:" + songs.size());
            if(mEchoWebSocketHandler!=null)
            mEchoWebSocketHandler.broadcast("!!!Finished, pages:" + links.size() + " songs:" + songs.size());
            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


            Gson gson = new Gson();
            String json = gson.toJson(songs);
            try {
                FileWriter writer = new FileWriter("kenget.json");
                writer.write(json);
                writer.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    private String cleanUrl(String url){
        return  url.replaceAll(" ", "%20");
    }

    public static void main(String[] args) {
        //1. Pick a URL from the frontier
        new Mp3Spider("http://www.shkarko.im", "http://shkarko.muzikpapare.com", null).start();
    }

}