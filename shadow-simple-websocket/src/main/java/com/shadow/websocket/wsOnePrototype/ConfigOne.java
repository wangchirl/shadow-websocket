package com.shadow.websocket.wsOnePrototype;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Classname ConfigOne
 * @Description TODO
 * @Date 2019/8/17 14:38
 * @Created by 伊人
 */
@Configuration
@EnableWebSocket
public class ConfigOne implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(marcoHandler(),"/marco")
                .setAllowedOrigins("*"); //setAllowedOrigins("*") 很重要
    }

    @Bean
    public MarcoHandler marcoHandler(){
        return new MarcoHandler();
    }
}
