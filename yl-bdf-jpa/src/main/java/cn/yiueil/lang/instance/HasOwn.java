package cn.yiueil.lang.instance;

import javax.persistence.Column;
/**
 * Author:YIueil
 * Date:2022/7/28 1:40
 * Description: 具有创建用户标识
 * @param <T> 主键类型
 */
public interface HasOwn<T> {
    void setCreateUser(T id);

    @Column(name = "create_user", columnDefinition = "创建用户")
    T getCreateUser();
}
