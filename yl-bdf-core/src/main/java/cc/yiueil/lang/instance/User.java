package cc.yiueil.lang.instance;


import java.io.Serializable;

/**
 * Author:YIueil
 * Date:2022/7/3 21:00
 * Description: 用户门面
 */
public interface User extends Serializable {
    Long getId();

    void setId(Long id);

    String getGuid();

    void setGuid(String guid);

    String getLoginName();

    void setLoginName(String loginName);

    String getUserName();

    void setUserName(String userName);
}
