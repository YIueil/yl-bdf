package cc.yiueil.session;

import cc.yiueil.lang.instance.Privilege;
import cc.yiueil.lang.instance.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * SessionContent 会话信息类
 * @author 弋孓 YIueil@163.com
 * @date 2023/5/31 23:21
 * @version 1.0
 */
@Getter
@Setter
public class SessionContent {
    /**
     * 会话session名称
     */
    public static final String SESSION_KEY = "YL-SESSION";

    /**
     * 会话ip
     */
    private String ip;

    /**
     * 当前会话的用户信息
     */
    private User user;

    /**
     * 权限列表
     */
    private List<Privilege> privilegeList;

}
