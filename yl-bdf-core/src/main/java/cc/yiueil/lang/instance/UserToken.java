package cc.yiueil.lang.instance;

import java.time.LocalDateTime;

/**
 * Author:YIueil
 * Date:2023/1/5 10:12
 * Description: 用户认证信息类
 */
public interface UserToken {
    Long getId();

    void setId(Long id);

    String getGuid();

    void setGuid(String guid);

    String getUserName();

    void setUserName(String userName);

    String getLoginName();

    void setLoginName(String loginName);

    LocalDateTime getCreateDateTime();

    void getCreateDateTime(LocalDateTime localDateTime);
}
