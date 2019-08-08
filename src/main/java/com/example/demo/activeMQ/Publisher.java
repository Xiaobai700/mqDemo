package com.example.demo.activeMQ;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Publisher {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void publish(String destination, String msg) {
        System.out.println("发布topic消息" + msg);
        // jmsMessagingTemplate.convertAndSend(destination,msg);
    }
}
