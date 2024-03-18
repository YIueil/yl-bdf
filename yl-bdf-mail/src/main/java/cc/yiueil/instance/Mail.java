package cc.yiueil.instance;

import javax.mail.Session;

/**
 * Mail mail接口
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/18 23:42
 */
public class Mail {
    /**
     * 创建mailBuilder对象
     *
     * @param session mailSession
     * @return 邮件构建对象
     */
    public static MailBuilder create(Session session) {
        return new MailBuilder(session);
    }
}
