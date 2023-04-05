package cc.yiueil.lang.instance;


import java.io.Serializable;

/**
 * Author:YIueil
 * Date:2022/7/3 21:00
 * Description: 用户门面
 * @param <T> 用户实体主键类型
 */
public interface User<T> extends Serializable {
    T getId();

    void setId(T id);

    String getGuid();

    void setGuid(String guid);

    String getLoginName();

    void setLoginName(String loginName);

    String getUserName();

    void setUserName(String userName);

    String getPassword();

    void setPassword(String password);
}
