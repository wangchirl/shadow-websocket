package com.shadow.websocket.wsThreeSockJs;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname MarcoHandler
 * @Description TODO
 * @Date 2019/8/17 14:37
 * @Created by 伊人
 */
public class MarcoHandler2 extends AbstractWebSocketHandler {

    private static Map<String,Object> sessionMap = new ConcurrentHashMap<>();

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message: " + message.getPayload());
//        Thread.sleep(2000);
        session.sendMessage(new TextMessage("Polo!"));
    }

    /**
     *  session存储客户端的连接信息
     * @param session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        if (session != null){
            sessionMap.put(session.getId(),session);
        }
        System.out.println("Connection established!  session ==> " + session);
        System.out.println(sessionMap);
    }

    /**
     *  断开链接时可以删除对象
     * @param session
     * @param status
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        if ( session != null){
            sessionMap.remove(session.getId());
        }
        System.out.println("Connection closed. Status: " + status);
        System.out.println(sessionMap);
    }

}