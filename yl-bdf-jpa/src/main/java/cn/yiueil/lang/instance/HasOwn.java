package cn.yiueil.lang.instance;

import javax.persistence.Column;

public interface HasOwn<T> {
    void setCreateUser(T id);

    @Column(name = "create_user")
    T getCreateUser();
}
