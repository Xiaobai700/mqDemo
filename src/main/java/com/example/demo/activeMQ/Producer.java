package com.example.demo.activeMQ;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class Producer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(String distinationName, String msg) {
        System.out.println("发送queue消息" + msg);
        Destination destination = new ActiveMQQueue(distinationName);
        jmsMessagingTemplate.convertAndSend(destination, msg);
    }

}
