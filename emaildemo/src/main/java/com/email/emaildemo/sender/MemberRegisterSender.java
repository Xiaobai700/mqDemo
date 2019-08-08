package com.email.emaildemo.sender;

import com.email.emaildemo.constants.Constants;
import com.email.emaildemo.entity.Member;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberRegisterSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送会员注册通知消息
     *
     * @param message 消息内容
     */
    public void sendMessage(Member message) throws Exception {
        rabbitTemplate.convertAndSend(Constants.MEMBER_TOPIC_EXCHANGE_NAME, Constants.MEMBER_TOPIC_EXCHANGE_ROUTE_KEY, message);
    }
}
