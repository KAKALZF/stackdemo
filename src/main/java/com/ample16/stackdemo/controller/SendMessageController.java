package com.ample16.stackdemo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author : JCccc
 * @CreateTime : 2019/9/3
 * @Description :
 **/
@RestController
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        for (int i = 0; i < 3; i++) {
            String messageId = String.valueOf(UUID.randomUUID());
//        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Map<String, Object> map = new HashMap<>();
            map.put("msg", messageId);
            map.put("count", 0);
            //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
            rabbitTemplate.convertAndSend("normalExchange", "normalRouting", map);
        }
        return "ok";
    }


}