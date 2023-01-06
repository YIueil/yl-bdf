package cc.yiueil.session;

import cc.yiueil.lang.instance.Privilege;
import cc.yiueil.lang.instance.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author:YIueil
 * Date:2022/7/3 21:08
 * Description: 会话信息
 */
@Getter
@Setter
public class SessionContent {
    public static final String SESSION_KEY = "YL-SESSION"; // 会话session名称

    private String ip; // 会话ip
    private User user; // 当前会话的用户信息

    private List<Privilege> privilegeList; // 权限列表

}
