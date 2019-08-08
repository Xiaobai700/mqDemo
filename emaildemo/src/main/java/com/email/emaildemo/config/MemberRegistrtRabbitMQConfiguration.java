package com.email.emaildemo.config;

import com.email.emaildemo.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberRegistrtRabbitMQConfiguration {
    private static Logger logger = LoggerFactory.getLogger(MemberRegistrtRabbitMQConfiguration.class);

    /**
     * 创建通配符交换机实例
     *
     * @return 通配符交换机实例
     */
    @Bean
    public TopicExchange topicExchange() {
        TopicExchange topicExchange = new TopicExchange(Constants.MEMBER_TOPIC_EXCHANGE_NAME);
        logger.info("【【【会员注册通配符交换机实例创建成功】】】");
        return topicExchange;
    }

    /**
     * 创建会员注册队列实例，并持久化
     *
     * @return 会员注册队列实例
     */
    @Bean
    public Queue memberRegisterQueue() {
        Queue memberRegisterQueue = new Queue(Constants.MEMBER_REGISTER_QUEUE_NAME, true);
        logger.info("【【【会员注册队列实例创建成功】】】");
        return memberRegisterQueue;
    }

    /**
     * 创建会员发送邮件队列实例，并持久化
     *
     * @return 会员发送邮件队列实例
     */
    @Bean
    public Queue memberSendMailQueue() {
        Queue memberRegisterQueue = new Queue(Constants.MEMBER_SEND_MAIL_QUEUE_NAME, true);
        logger.info("【【【会员发送邮件队列实例创建成功】】】");
        return memberRegisterQueue;
    }

    /**
     * 绑定会员注册队列到交换机
     *
     * @return 绑定对象
     */
    @Bean
    public Binding memberRegisterBinding() {
        Binding binding = BindingBuilder.bind(memberRegisterQueue()).to(topicExchange()).with(Constants.MEMBER_REGISTER_QUEUE_ROUTE_KEY);
        logger.info("【【【会员注册队列与交换机绑定成功】】】");
        return binding;
    }

    /**
     * 绑定会员发送邮件队列到交换机
     *
     * @return 绑定对象
     */
    @Bean
    public Binding memberSendMailBinding() {
        Binding binding = BindingBuilder.bind(memberSendMailQueue()).to(topicExchange()).with(Constants.MEMBER_SEND_MAIL_QUEUE_ROUTE_KEY);
        logger.info("【【【会员发送邮件队列与交换机绑定成功】】】");
        return binding;
    }
}
