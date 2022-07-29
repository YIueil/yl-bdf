package cn.yiueil.lang.instance;

import javax.persistence.Column;

public interface HasParent<T> {
    void setParentId(T parentId);

    @Column(name = "parent_id")
    T getParentId();
}
