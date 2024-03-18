package cc.yiueil.factory;

import lombok.Getter;
import lombok.Setter;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * MailSessionFactory mailSession工厂类
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/18 22:42
 */
@Getter
@Setter
public class MailSessionFactory {
    private String stmpHost;
    private String stmpPort;
    private Boolean stmpAuth;
    private String username;
    private String password;
    private String useSsl;
    private String useTls;
    private Boolean enableDebug;

    /**
     * 通过属性值获取 mailSession
     * @return mailSession
     */
    public Session getSession() {
        Properties props = new Properties();
        // 用户和密码
        props.put("mail.username", username);
        props.put("mail.password", password);
        // SMTP主机名
        props.put("mail.smtp.host", stmpHost);
        // 主机端口号
        props.put("mail.smtp.port", stmpPort);
        // 是否需要用户认证
        props.put("mail.smtp.auth", stmpAuth);
        // 启用SSL证书 注意替换服务端口
        props.put("mail.smtp.ssl.enable", useSsl);
        // 启用TLS加密
        props.put("mail.smtp.starttls.enable", useTls);
        // 启动debug
        props.put("mail.debug", enableDebug);
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    /**
     * 通过配置文件加载获取 mailSession
     * @return mailSession
     */
    public Session getSessionByProperties() {
        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("properties/mail.properties")) {
            if (resourceAsStream == null) {
                throw new RuntimeException("Fail load properties: classpath: properties/mail.properties.");
            }
            Properties props = new Properties();
            props.load(resourceAsStream);
            return Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load mail properties.");
        }
    }
}
