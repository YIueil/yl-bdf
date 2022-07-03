package cn.yiueil.entity.instance;


import java.io.Serializable;

/**
 * Author:YIueil
 * Date:2022/7/3 21:00
 * Description: 用户实体接口
 */
public interface User extends Serializable {
    Long getId();

    String getGuid();

    String getLoginName();
}
