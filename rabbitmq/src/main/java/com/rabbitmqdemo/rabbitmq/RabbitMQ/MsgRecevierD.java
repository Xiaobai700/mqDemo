package com.rabbitmqdemo.rabbitmq.RabbitMQ;

import com.rabbitmqdemo.rabbitmq.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_D)
public class MsgRecevierD {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @RabbitHandler
    public void process(Message message){
        MessageProperties messageProperties = message.getMessageProperties();
        System.out.println(rabbitTemplate.receiveAndConvert(RabbitConfig.QUEUE_D));
    }
}
