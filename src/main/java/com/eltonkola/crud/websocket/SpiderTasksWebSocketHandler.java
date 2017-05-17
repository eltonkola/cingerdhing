package com.eltonkola.crud.websocket;
import com.eltonkola.crud.spider.SpiderTaskManager;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpiderTasksWebSocketHandler extends TextWebSocketHandler {

    private static Logger logger = LoggerFactory.getLogger(SpiderTasksWebSocketHandler.class);

    SpiderTaskManager mSpiderTaskManager;

    private Map<String, WebSocketSession> mClients = new HashMap<>();

    public SpiderTasksWebSocketHandler(SpiderTaskManager spiderTaskManager) {
        mSpiderTaskManager =spiderTaskManager;
        mSpiderTaskManager.setClient(new SpiderTaskManager.ClientStateUpdate() {
            @Override
            public void update(String burimiId, String msg) {
                broadcast(burimiId, msg);
            }
        });
    }

    public void broadcast(String id, String msg){
        if(mClients.size() == 0){
            return;
        }

        for (Map.Entry<String, WebSocketSession> entry : mClients.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            if(entry.getKey().equalsIgnoreCase( id + "_client_" + entry.getValue().getId())){
                try {
                    entry.getValue().sendMessage(new TextMessage(msg));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.debug("Opened new session in instance " + this);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String msg = message.getPayload();

        if("start".equalsIgnoreCase(msg)){
            String sId = msg.replace("register_burimi_id=","");
            startSpider(sId, session);
            session.sendMessage(new TextMessage("Started the spider.."));
        }else if(msg.startsWith("register_burimi_id=")){
            String sId = msg.replace("register_burimi_id=","");
            registerForBurimiSpiderTask(sId, session);
            logger.debug("Register for tasts for: " + sId);
        } else {
            String echoMessage = String.format("Did you say %s?", msg);
            logger.debug(echoMessage);
            session.sendMessage(new TextMessage(echoMessage));
        }
    }

    private void registerForBurimiSpiderTask(String sId, WebSocketSession session) {
        mClients.put(sId + "_client_" + session.getId(), session);
        mSpiderTaskManager.getSpiderForTask(sId);
    }

    private void startSpider(String sId, WebSocketSession session){
        mSpiderTaskManager.startSpiderForTask(sId);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        mClients.values().remove(session);
        session.close(CloseStatus.SERVER_ERROR);
    }

}