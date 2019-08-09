package com.email.emaildemo.receiver;

import com.email.emaildemo.constants.Constants;
import com.email.emaildemo.entity.Member;
import com.email.emaildemo.mail.EMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Component
@RabbitListener(queues = Constants.MEMBER_SEND_MAIL_QUEUE_NAME)
public class MemberSendMailReceiver {

    private static Logger logger = LoggerFactory.getLogger(MemberSendMailReceiver.class);

    @RabbitHandler
    @Transactional
    public void sendMail(Member member) throws Exception {
        logger.info("会员用户名:{}，注册成功,准备发送邮件...", member.getUsername());
        System.out.println("发邮件的方法！");
        //执行发送邮件操作
        new EMailSender()
                .setTitle("通知邮件")
                .setContent("收到邮件回复！")
                .setContentType(Constants.SEND_MAIL_HTML_TYPE)
                .setSendMailTargets(new ArrayList<String>() {{
                    add("2504964152@qq.com");
                }}).send();
    }

}
