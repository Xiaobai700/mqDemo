package com.email.emaildemo.receiver;

import com.email.emaildemo.constants.Constants;
import com.email.emaildemo.entity.Member;
import com.email.emaildemo.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RabbitListener(queues = Constants.MEMBER_REGISTER_QUEUE_NAME)
public class MemberRegisterReceiver {
    @Autowired
    private MemberRepository memberRepository;

    private static Logger logger = LoggerFactory.getLogger(MemberRegisterReceiver.class);

    @RabbitHandler
    @Transactional
    public void handler(Member member) throws Exception {
        logger.info("会员用户名: {}, 注册成功, 准备创建会员信息...", member.getUsername());

        //保存会员消息
        memberRepository.save(member);

    }
}
