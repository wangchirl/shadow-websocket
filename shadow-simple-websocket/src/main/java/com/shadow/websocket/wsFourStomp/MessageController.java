package com.shadow.websocket.wsFourStomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * @Classname MessageController
 * @Description TODO
 * @Date 2019/8/17 16:01
 * @Created by 伊人
 */
@Controller
public class MessageController {

    //给指定的用户回复消息 simpMessageSendingOperations.convertAndSendToUser("1", "/message", "测试convertAndSendToUser");
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;


    @MessageMapping("/handleMsg")
    public void handleMsg(WsMessage incoming)
    {
        System.out.println("接收到客户端消息啦:"+incoming.getMessage());
        simpMessageSendingOperations.convertAndSendToUser("admin","/queue/message","回复admin用户的指定消息");
    }

    @MessageMapping("/msg")
    @SendTo("/queue/msg")
    public WsMessage msg(WsMessage message) {
        System.out.println("接收到信息" + message.getMessage());
        message.setMessage("测试回复消息");
        return message;
    }
    @SubscribeMapping("/pushMsg")
    public WsMessage pushMsg()
    {
        WsMessage outing = new WsMessage();
        outing.setMessage("服务器推送消息啦...");
        return outing;
    }

    //通过Principal获取指定用户
    @MessageMapping("/test")
    public void test(Principal principal,WsMessage wsMessage){
        System.out.println("测试回复指定用户信息");
        simpMessageSendingOperations.convertAndSendToUser(principal.getName(),"/message","回复"+principal.getName()+"用户的信息");
    }

    @MessageExceptionHandler(Exception.class)
    @SendToUser("/queue/errors")
    public Exception handleExceptions(Exception t){
        t.printStackTrace();
        return t;
    }
//    @Scheduled(fixedRate=10000)
    public void sendRandomNumber() {
        WsMessage random = new WsMessage();
        random.setMessage("Random # : " + (Math.random() * 100));
        simpMessageSendingOperations.convertAndSend("/topic/marco", random);
    }


}
