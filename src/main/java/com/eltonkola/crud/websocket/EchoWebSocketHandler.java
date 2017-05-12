package com.eltonkola.crud.websocket;
import com.eltonkola.crud.crawler.SongCrawler;
import com.eltonkola.crud.crawler.MyCrawlerFactory;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.socket.CloseStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Echo messages by implementing a Spring {@link WebSocketHandler} abstraction.
 */
public class EchoWebSocketHandler extends TextWebSocketHandler {

    private static Logger logger = LoggerFactory.getLogger(EchoWebSocketHandler.class);


    private List<WebSocketSession> mClients = new ArrayList<>();

    @Autowired
    MyCrawlerFactory mMyCrawlerFactory;

    public EchoWebSocketHandler() {

    }

    public void broadcast(String msg){
        if(mClients.size() == 0){
            return;
        }

        for(WebSocketSession sess : mClients){
            try {
                sess.sendMessage(new TextMessage(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.debug("Opened new session in instance " + this);
        mClients.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {

        String msg = message.getPayload();

        if("start".equalsIgnoreCase(msg)){
            startSpider();
            session.sendMessage(new TextMessage("Started the spider.."));
        }else {

            String echoMessage = String.format("Did you say %s?", msg);
            logger.debug(echoMessage);
            session.sendMessage(new TextMessage(echoMessage));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception)
            throws Exception {
        mClients.remove(session);
        session.close(CloseStatus.SERVER_ERROR);
    }


    private void startSpider(){
        try {
            String crawlStorageFolder = "/tmp";
            int numberOfCrawlers = 1;

            CrawlConfig config = new CrawlConfig();

            config.setCrawlStorageFolder(crawlStorageFolder);
            config.setPolitenessDelay(10000);//10000
            config.setMaxDepthOfCrawling(3);//-1 = unlimited
            config.setMaxPagesToFetch(30); // -1 = unlimited

            config.setUserAgentString("Mozilla/5.0 (Android 4.4; Mobile; rv:41.0) Gecko/41.0 Firefox/41.0");

            config.setIncludeBinaryContentInCrawling(true);
            config.setResumableCrawling(false);

            PageFetcher pageFetcher = new PageFetcher(config);
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

            controller.addSeed("http://www.shkarko.im");

            String[] crawlDomains = {"http://www.shkarko.im", "http://shkarko.muzikpapare.com"};

            SongCrawler.configure(crawlDomains, "/home/elton/Desktop/ekola/cingerdhing/kenget");


            MyCrawlerFactory factory = mMyCrawlerFactory;//metadata, repository
            controller.startNonBlocking(factory, numberOfCrawlers);

            //controller.start(SongCrawler.class, numberOfCrawlers);





        }catch (Exception e){
            e.printStackTrace();
        }
    }

}