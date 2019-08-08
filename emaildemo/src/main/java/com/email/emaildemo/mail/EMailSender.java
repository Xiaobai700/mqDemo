package com.email.emaildemo.mail;

import com.email.emaildemo.constants.Constants;
import com.email.emaildemo.entity.MailEntity;
import com.email.emaildemo.utils.PropertiesUtils;
import org.apache.commons.lang.StringUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.List;
import java.util.Properties;

public class EMailSender {
    /**
     * 邮件实体
     */
    private static MailEntity mail = new MailEntity();

    /**
     * 设置邮件标题
     *
     * @param title 标题信息
     * @return
     */
    public EMailSender setTitle(String title) {
        mail.setTitle(title);
        return this;
    }

    /**
     * 设置邮件内容
     *
     * @param content
     * @return
     */
    public EMailSender setContent(String content) {
        mail.setContent(content);
        return this;
    }

    /**
     * 设置邮件格式
     *
     * @param typeEnum
     * @return
     */
    public EMailSender setContentType(String typeEnum) {
        mail.setContentType(typeEnum);
        return this;
    }

    /**
     * 设置请求目标邮件地址
     *
     * @param targets
     * @return
     */
    public EMailSender setSendMailTargets(List<String> targets) {
        mail.setList(targets);
        return this;
    }

    /**
     * 执行发送邮件
     *
     * @throws Exception 如果发送失败会抛出异常信息
     */
    public void send() throws Exception {
        //校验发送邮件对象参数是否设置
        this.checkMailParams(mail);
        //读取/resource/mail.properties文件内容
        final PropertiesUtils properties;
        properties = new PropertiesUtils("mail");
        // 创建Properties 类用于记录邮箱的一些属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", properties.getValue("send.mail.smtp.service"));
        //设置端口号，QQ邮箱两个端口465/587
        props.put("mail.smtp.port", properties.getValue("send.mail.smtp.prot"));
        // 设置发送邮箱
        props.put("mail.user", properties.getValue("send.mail.from.address"));
        // 设置授权码
        props.put("mail.password", properties.getValue("send.mail.from.smtp.pwd"));

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        String nickName = MimeUtility.encodeText(properties.getValue("send.mail.from.nickname"));
        InternetAddress form = new InternetAddress(nickName + " <" + props.getProperty("mail.user") + ">");
        message.setFrom(form);

        // 设置邮件标题
        message.setSubject(mail.getTitle());
        //html发送邮件
        if (mail.getContentType().equals(Constants.SEND_MAIL_HTML_TYPE)) {
            // 设置邮件的内容体 默认使用html方式发送
            message.setContent(mail.getContent(), StringUtils.isBlank(mail.getContentType()) ? Constants.SEND_MAIL_HTML_TYPE : mail.getContentType());
        } else if (mail.getContentType().equals(Constants.SEND_MAIL_TEXT_TYPE)) {
            // Text文本方式发送
            message.setText(mail.getContent());
        }
        //发送邮箱地址
        List<String> targets = mail.getList();
        for (String target : targets) {
            try {
                // 设置收件人的邮箱
                InternetAddress to = new InternetAddress(target);
                message.setRecipient(Message.RecipientType.TO, to);
                // 发送邮件
                Transport.send(message);
            } catch (Exception e) {
                continue;
            }
        }
    }

    /**
     * 校验发送邮件的一些参数是否设置
     *
     * @param mail 邮件发送对象
     * @throws Exception
     */
    private void checkMailParams(MailEntity mail) throws Exception {
        if (StringUtils.isBlank(mail.getTitle())) {
            throw new Exception("抱歉，邮件标题不能为空，请先设置邮件标题！");
        }

        if (StringUtils.isBlank(mail.getContent())) {
            throw new Exception("抱歉，邮件内容不能为空，请先设置邮件内容！");
        }

        if (mail.getList().size() == 0) {
            throw new Exception("抱歉，邮件接收方不能为空，请先设置邮件接收目标对象！");
        }
    }
}
