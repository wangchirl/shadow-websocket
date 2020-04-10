package com.shadow.websocket.wsThreeSockJs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Classname WebSocketConfig
 * @Description TODO
 * @Date 2019/8/17 13:27
 * @Created by 伊人
 */
@Configuration
@EnableWebSocket
public class ConfigThree implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(marcoHandler2(), "/marco2")
                .setAllowedOrigins("*").withSockJS(); //setAllowedOrigins("*")很重要 withSockJS()支持sockJS
    }

    @Bean
    public MarcoHandler2 marcoHandler2(){
        return new MarcoHandler2();
    }


}
