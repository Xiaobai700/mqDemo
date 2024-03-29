package com.rabbitmqdemo.rabbitmq.RabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender_Headers {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @param exchangeName 交换机名称
     * @param message      消息信息
     */
    public void send(String exchangeName, Message message) {
        rabbitTemplate.convertAndSend(exchangeName,null, message);
    }
}
