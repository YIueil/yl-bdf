package cc.yiueil.service;

import cc.yiueil.instance.Mail;
import cc.yiueil.instance.MailBuilder;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.File;
import java.util.List;

/**
 * MailService 邮件服务
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/18 23:33
 */
@Slf4j
@Setter
public class MailService {
    private Session session;

    /**
     * 自定义构建: 创建出MailBuilder对象
     * Mail.create(session)
     * .setFrom(from)
     * .addTo(toList)
     * .setSubject(subject)
     * .setBody(body)
     * .setHtml(isHtml)
     * .addAttachment(attachments)
     * .send();
     *
     * @return MailBuilder对象
     */
    public MailBuilder create() {
        return Mail.create(session);
    }

    /**
     * @param subject     主题
     * @param body        主体内容
     * @param isHtml      是否html
     * @param toList      收件方
     * @param attachments 附件列表
     */
    public void send(String subject, String body, boolean isHtml, List<String> toList, List<File> attachments) {
        try {
            Mail.create(session)
                    .addTo(toList)
                    .setSubject(subject)
                    .setBody(body)
                    .setHtml(isHtml)
                    .addAttachment(attachments)
                    .send();
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }
    }
}
