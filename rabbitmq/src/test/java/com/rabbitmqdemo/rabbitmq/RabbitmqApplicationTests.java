package com.rabbitmqdemo.rabbitmq;

import com.rabbitmqdemo.rabbitmq.RabbitMQ.MsgProducer;
import com.rabbitmqdemo.rabbitmq.RabbitMQ.Sender_Headers;
import com.rabbitmqdemo.rabbitmq.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {
    @Resource
    private MsgProducer msgProducer;

    @Resource
    private Sender_Headers sender_headers;

    @Test
    public void contextLoads() {
       // msgProducer.sendMsg("hello");

        Map<String,Object> map_any = new HashMap<>();
        map_any.put("name","jack");
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().putAll(map_any);
        Message message = new Message("hello,one".getBytes(),messageProperties);
        sender_headers.send(RabbitConfig.EXCHANGE_D,message);

    }

}
