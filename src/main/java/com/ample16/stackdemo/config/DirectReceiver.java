package com.ample16.stackdemo.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Random;

@Component

public class DirectReceiver {
    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @RabbitHandler
    @RabbitListener(queues = "TestDeadDirectQueue")//监听的死信队列名称 TestDirectDeadQueue
    public void processDeadQueue(Map testMessage) {
        System.out.println("DirectDeadReceiver消费者收到消息  : " + testMessage.toString() + new Date());
    }

    @RabbitHandler
    @RabbitListener(queues = "normalQueue")//监听的死信队列名称 normalQueue
    public void normalQueueComsumer(Map testMessage) {
//        System.out.println("normalQueue消费者收到消息  : " + testMessage.toString() + new Date());
        if (isNotifySuccess()) {
            System.out.println("normalQueue|suc|" + testMessage.toString() + "|" + new Date());
        } else {
            System.out.println("normalQueue|fail|" + testMessage.toString() + "|" + new Date());
            Integer count = (Integer) testMessage.get("count");
            if (count < 3) {
                //消费失败且未超过重试次数,重新发送到交换机processFailExchange
                testMessage.put("count", count + 1);
                rabbitTemplate.convertAndSend("processFailExchange", "processFailRouting", testMessage);
            } else {
                System.out.println("超过重复次数,放弃消息:" + testMessage.toString() + "|" + new Date());
            }
        }
    }

   /* @RabbitHandler
    @RabbitListener(queues = "processFailQueue")
    public void processFailQueueComsumer(Map testMessage) {
        System.out.println("processFailQueue消费者收到消息  : " + testMessage.toString() + new Date());
    }
*/

    /**
     * 随机模拟成功失败,偶数成功,奇数失败
     *
     * @return
     */
    public Boolean isNotifySuccess() {
//        int i = new Random().nextInt(10);
//        return i % 2 == 0 ? true : false;
        return false;
    }
}