package com.eltonkola.crud.crawler;

import com.eltonkola.crud.service.SongServiceInterface;
import com.eltonkola.crud.websocket.EchoWebSocketHandler;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@EnableAutoConfiguration
@ComponentScan
public class MyCrawlerFactory implements CrawlController.WebCrawlerFactory {

    @Autowired
    SongServiceInterface mSongServiceInterface;

    @Autowired
    EchoWebSocketHandler mEchoWebSocketHandler;

//    public MyCrawlerFactory(Map<String, String> metadata, SongServiceInterface repository) {
//        this.metadata = metadata;
//        this.repository = repository;
//    }

    @Override
    public SongCrawler newInstance() {
        return new SongCrawler(mEchoWebSocketHandler, mSongServiceInterface);
    }
}