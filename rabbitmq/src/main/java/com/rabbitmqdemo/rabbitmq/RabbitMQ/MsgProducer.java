package com.rabbitmqdemo.rabbitmq.RabbitMQ;

import com.rabbitmqdemo.rabbitmq.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        /**
         * HeadersExchange：通过添加属性key-value匹配
         * */
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_D,null,content);
        /**
         *TopicExchange:多关键字匹配
         * */
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_C,"com.register.wsh", content, correlationId);
        /**
         * DirectExchange:按照routingkey分发到指定队列*/
        //rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_A, content, correlationId);
        /**
         * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
         * 被Exchange_B绑定的队列都会收到这个消息*/
        // rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_B,RabbitConfig.ROUTINGKEY_B,content,correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("消息成功消费");
        } else {
            System.out.println("消息消费失败:" + s);
        }
    }
}
