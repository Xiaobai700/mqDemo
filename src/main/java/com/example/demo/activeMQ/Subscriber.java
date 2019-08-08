package com.example.demo.activeMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class Subscriber {
    @JmsListener(destination = "test.topic", containerFactory = "myJmsListenerContainerFactory")
    public void subscribe(String txt) {
        System.out.println("收到订阅的消息" + txt);
    }
}
