package com.ample16.stackdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 重试需求：回调通知,如果通知失败失败,重试3次,每10s一次,3次均失败则放弃
 * ：rabbitMq的配置
 */
@Configuration
public class RetryRabbitConfig {
    //重试次数
    public static int retryCount = 3;
    //过期时间
    public static Long expiredTime = 10000L;

    /**
     * 正常的消费队列相关，包括交换机,队列,以及两者的绑定
     */
    @Bean
    public Queue NormalQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        return new Queue("normalQueue", true, false, false);
    }

    @Bean
    DirectExchange NormalExchange() {
        return new DirectExchange("normalExchange", true, false);
    }

    @Bean
    Binding bindingNormalDirect() {
        //正常消费队列和交换机绑定, 并设置用于匹配键：normalRouting
        return BindingBuilder.bind(NormalQueue()).to(NormalExchange()).with("normalRouting");
    }

    /**
     * 消费失败队列相关，包括交换机,队列,以及两者的绑定
     */
    @Bean
    public Queue ProcessFailQueue() {
        //设置其的死信交换机为普通队列的交换机,消息到期后消息会死亡,然后投放到死信交换机
        String dlxName = "normalExchange";
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", dlxName);
        map.put("x-message-ttl", expiredTime);
        //死信的路由key和正常消息的路由key一致,消息会被投放到正常队列重新消费
        map.put("x-dead-letter-routing-key", "normalRouting");
        return new Queue("processFailQueue", true, false, false, map);
    }

    @Bean
    DirectExchange ProcessFailExchange() {
        return new DirectExchange("processFailExchange", true, false);
    }

    @Bean
    Binding bindingProcessFailDirect() {
        //消费失败队列和交换机绑定, 并设置用于匹配键：normalRouting
        return BindingBuilder.bind(ProcessFailQueue()).to(ProcessFailExchange()).with("processFailRouting");
    }


}