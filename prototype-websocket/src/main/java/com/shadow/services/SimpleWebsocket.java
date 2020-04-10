package com.shadow.services;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shadow
 * @create 2020-04-10
 * @description
 */

@ServerEndpoint("/simpleWs")
public class SimpleWebsocket {
    // 记录当前在线人数
    private static AtomicInteger onlineCount = new AtomicInteger(0);


    // 保存每一个客户端对应的对象
    private static CopyOnWriteArrayList<SimpleWebsocket> clientsSet = new CopyOnWriteArrayList<>();

    // 与某个客户端的连接会话，需要经过它来给客户端发送数据
    private Session session;


    /**
     * 连接建立成功调用的方法
     * @param session 可选的参数，session为与某个客户端的连接会话
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        clientsSet.add(this);
        addOnlineCount();
        System.out.println("新客户端加入，在线人数：" + onlineCount);
    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        clientsSet.remove(this);
        decOnlineCount();
        System.out.println("某个连接关闭，在线人数：" + onlineCount);
    }


    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("收到客户端消息：" + message);
        // 消息群发
        for (SimpleWebsocket simpleWebsocket : clientsSet) {
            try {
                simpleWebsocket.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

        }
    }


    /**
     * 发生错误时调用
     * @param session 当前的会话
     * @param e 错误
     */
    @OnError
    public void onError(Session session,Throwable e){
        System.out.println("发送异常");
        clientsSet.remove(session);
        e.printStackTrace();
    }


    /**
     * 发送消息的方法
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }





    /**
     * 在线人数减一
     */
    public void decOnlineCount(){
        onlineCount.getAndDecrement();
    }


    /**
     * 在线人数加一
     */
    public void addOnlineCount(){
        onlineCount.getAndIncrement();
    }

}
