package com.eltonkola.crud.config;

import com.eltonkola.crud.spider.SpiderTaskManager;
import com.eltonkola.crud.websocket.SpiderTasksWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    SpiderTaskManager spiderTaskManager;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoWebSocketHandler(), "/spider_tasks").withSockJS();
    }


    @Bean
    public SpiderTasksWebSocketHandler echoWebSocketHandler() {
        return new SpiderTasksWebSocketHandler(spiderTaskManager);
    }

}