package cc.yiueil.instance;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MailSend mail发送对象
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/18 23:53
 */
public class MailBuilder {
    private final Session session;
    private String subject;
    private String body;
    private boolean isHtml;
    private final List<String> toList = new ArrayList<>();
    private final List<File> attachments = new ArrayList<>();

    public MailBuilder(Session session) {
        this.session = session;
    }

    public MailBuilder addTo(String... tos) {
        toList.addAll(Arrays.asList(tos));
        return this;
    }

    public MailBuilder addTo(List<String> tos) {
        toList.addAll(tos);
        return this;
    }

    public MailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public MailBuilder setHtml(boolean isHtml) {
        this.isHtml = isHtml;
        return this;
    }

    public MailBuilder addAttachment(File attachment) {
        attachments.add(attachment);
        return this;
    }

    public MailBuilder addAttachment(List<File> attachment) {
        attachments.addAll(attachment);
        return this;
    }

    /**
     * 发送
     *
     * @throws MessagingException 消息异常
     */
    public void send() throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(session.getProperty("mail.username")));
        for (String to : toList) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        }
        message.setSubject(subject);

        if (attachments.isEmpty()) {
            if (isHtml) {
                message.setContent(body, "text/html;charset=utf-8");
            } else {
                message.setText(body);
            }
        } else {
            // 创建一个MimeBodyPart对象，用于存放邮件正文
            MimeBodyPart contentPart = new MimeBodyPart();
            if (isHtml) {
                contentPart.setContent(body, "text/html;charset=utf-8");
            } else {
                contentPart.setText(body);
            }
            // 创建包含多个附件的MimeMultipart对象
            Multipart multipart = new MimeMultipart();
            for (File attachment : attachments) {
                // 创建一个MimeBodyPart对象，用于存放附件
                MimeBodyPart fileBodyPart = new MimeBodyPart();
                DataSource dataSource = new FileDataSource(attachment);
                fileBodyPart.setDataHandler(new DataHandler(dataSource));
                try {
                    fileBodyPart.setFileName(MimeUtility.encodeText(attachment.getName(), "UTF-8", "B"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // 将MimeBodyPart对象添加到Multipart容器中
                multipart.addBodyPart(fileBodyPart);
            }
            // 将邮件正文和所有附件添加到Multipart容器中
            multipart.addBodyPart(contentPart);
            // 设置邮件内容为Multipart对象
            message.setContent(multipart);
        }
        Transport.send(message);
    }
}
