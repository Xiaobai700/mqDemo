package com.example.demo.activeMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @JmsListener(destination = "test.queue")
    public void receiveMsg(String txt) {
        System.out.println("收到消息" + txt);
    }
}
