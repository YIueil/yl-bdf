package cc.yiueil;

import cc.yiueil.factory.MailSessionFactory;
import org.junit.jupiter.api.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Test01 {
    @Test
    public void test1() throws MessagingException {
        // 服务器地址:
        String smtp = "smtp.163.com";
        // 登录用户名:
        String username = "starrail0313@163.com";
        // 登录口令:
        String password = "";
        // 连接到SMTP服务器587端口:
        Properties props = new Properties();
        props.put("mail.smtp.host", smtp); // SMTP主机名
        props.put("mail.smtp.port", "465"); // 主机端口号
        props.put("mail.smtp.auth", "true"); // 是否需要用户认证
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.starttls.enable", "true"); // 启用TLS加密
        props.put("mail.debug", "true"); // 开启debugger
        // 获取Session实例:
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        // 设置发送方地址:
        message.setFrom(new InternetAddress("starrail0313@163.com"));
        // 设置接收方地址:
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("yiueil@163.com"));
        // 设置邮件主题:
        message.setSubject("Hello 这是一封测试邮件", "UTF-8");
        // 设置邮件正文:
        message.setText("Hi YIueil... 这是一封测试邮件", "UTF-8");
        // 发送:
        Transport.send(message);
    }

    @Test
    public void test2() throws MessagingException {
        MailSessionFactory mailSessionFactory = new MailSessionFactory();
        Session session = mailSessionFactory.getSessionByProperties();
        MimeMessage message = new MimeMessage(session);
        // 设置发送方地址:
        message.setFrom(new InternetAddress("starrail0313@163.com"));
        // 设置接收方地址:
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("yiueil@163.com"));
        // 设置邮件主题:
        message.setSubject("Hello 这是一封测试邮件", "UTF-8");
        // 设置邮件正文:
        message.setText("Hi YIueil... 这是一封测试邮件", "UTF-8");
        // 发送:
        Transport.send(message);
    }
}
