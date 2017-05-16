package com.eltonkola.crud.websocket;
import com.eltonkola.crud.spider.Mp3Spider;
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

        Mp3Spider mSpider = new Mp3Spider("http://www.shkarko.im", "http://shkarko.muzikpapare.com", this);

        mSpider.start();
    }


    public static void main(String[] args){
        new EchoWebSocketHandler().startSpider();
    }
}