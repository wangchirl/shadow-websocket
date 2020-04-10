package com.shadow.websocket.controller;

import com.shadow.websocket.wsFourStomp.WsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname DemoController
 * @Description TODO
 * @Date 2019/8/26 17:30
 * @Created by 伊人
 */
@RestController
@RequestMapping("/demo")
public class DemoController {


    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    private int count = 0;

    /**
     * 广播
     * @return
     */
    @RequestMapping("/topic")
    public String topic() {
        WsMessage wsMessage = new WsMessage();
        count++;
        wsMessage.setMessage("访问记录数" + count);
        simpMessageSendingOperations.convertAndSend("/topic/marco", wsMessage); // 前端订阅 /topic/marco
        return "success";
    }

    /**
     * 点对点
     * @return
     */
    @RequestMapping("/user")
    public String user() {
        WsMessage wsMessage = new WsMessage();
        count++;
        wsMessage.setMessage("访问记录数" + count);
        simpMessageSendingOperations.convertAndSendToUser("admin","/marco",wsMessage); // 前端订阅 /user/{admin}/marco
        return "success";
    }
}
