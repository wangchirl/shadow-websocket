package com.shadow.websocket.wsFourStomp;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author shadow
 * @create 2020-04-10
 * @description
 */
public class MyHandshakeInterceptor1 implements HandshakeInterceptor {


    /**
     * 首先会进入到这里来 - 这里可以进行客户端验证，返回 false 表示拒绝客户端连接
      * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param map
     * @return true 表示接受客户端请求，false 表示拒绝客户端连接
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {

        System.out.println("before hand shake...1");

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("after hand shake...1");
    }
}
