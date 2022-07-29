package cn.yiueil.lang.instance;

import javax.persistence.Id;

/**
 * Author:YIueil
 * Date:2022/7/28 1:39
 * Description: 有id的类
 * @param <T> 主键类型
 */
public interface HasId<T> {
    void setId(T id);

    @Id
    T getId();
}
