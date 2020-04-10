package com.shadow.websocket.wsFourStomp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.security.Principal;

/**
 * @Classname WebSocketStompConfig
 * @Description TODO
 * @Date 2019/8/17 15:52
 * @Created by 伊人
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp")
                .addInterceptors(handshakeInterceptor1(),handshakeInterceptor2()) // 这里的拦截器首先起作用，可以配置多个，先后顺序来拦截
                .setAllowedOrigins("*")
                .withSockJS(); //.setAllowedOrigins("*")
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //表明在topic、queue、user这三个域上可以向客户端发消息。
        registry.enableSimpleBroker("/topic","/queue","/user");
        //客户端向服务端发起请求时，需要以/app为前缀。
        registry.setApplicationDestinationPrefixes("/app");
        //给指定用户发送一对一的消息前缀是/user/。
        registry.setUserDestinationPrefix("/user/");
    }

    /**
     * 1、设置拦截器
     * 2、首次连接的时候，获取其Header信息，利用Header里面的信息进行权限认证
     * 3、通过认证的用户，使用 accessor.setUser(user); 方法，
     * 将登陆信息绑定在该 StompHeaderAccessor 上，在Controller方法上可以获取 StompHeaderAccessor 的相关信息
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //1、判断是否首次连接
                if (StompCommand.CONNECT.equals(accessor.getCommand())){
                    System.out.println("StompCommand.CONNECT");
                    //2、判断用户名和密码
                    String username = accessor.getNativeHeader("username").get(0);
//                    String password = accessor.getNativeHeader("passcode").get(0);
//                    User user = (User)accessor.getSessionAttributes().get(Constants.WEBSOCKET_USER_KEY);
                    if ( username != null){
                        Principal principal = new Principal() {
                            @Override
                            public String getName() {
                                return username;
                            }
                        };
                        accessor.setUser(principal);
                        return message;
                    }else {
                        return null;
                    }
                }
                //不是首次连接，已经登陆成功
                return message;
            }

        });
    }



    @Bean
    public HandshakeInterceptor handshakeInterceptor1(){
        return new MyHandshakeInterceptor1();
    }


    @Bean
    public HandshakeInterceptor handshakeInterceptor2(){
        return new MyHandshakeInterceptor2();
    }

}
