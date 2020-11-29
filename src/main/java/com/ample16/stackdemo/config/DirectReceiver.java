package com.ample16.stackdemo.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component

public class DirectReceiver {

   /* @RabbitHandler
    @RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
    public void process(Map testMessage) {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }*/


    @RabbitHandler
    @RabbitListener(queues = "TestDeadDirectQueue")//监听的死信队列名称 TestDirectDeadQueue
    public void processDeadQueue(Map testMessage) {
        System.out.println("DirectDeadReceiver消费者收到消息  : " + testMessage.toString()+new Date());
    }
}