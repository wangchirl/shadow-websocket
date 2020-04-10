package com.shadow.websocket.wsTwoPrototype;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Classname WebSocketConfig
 * @Description TODO
 * @Date 2019/8/17 13:27
 * @Created by 伊人
 */
@Configuration
@EnableWebSocket
public class ConfigTwo {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
