package cn.yiueil.data;

import cn.yiueil.lang.instance.HasOwn;

/**
 * Author:YIueil
 * Date:2022/7/27 1:35
 * Description: todo 提供设置创建人信息
 */
public interface OwnDao {
    default void generatorCreateUser(Object entity) {
        if (entity instanceof HasOwn) {
            HasOwn ownEntity = (HasOwn) entity;
            if(ownEntity.getCreateUser() == null)
            {
                ownEntity.setCreateUser(null);
            }
        }
    }
}
