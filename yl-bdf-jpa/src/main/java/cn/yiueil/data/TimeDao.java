package cn.yiueil.data;

import cn.yiueil.lang.instance.HasTime;

import java.time.LocalDateTime;

/**
 * Author:YIueil
 * Date:2022/7/27 1:37
 * Description: 提供日期创建和修改方法
 */
public interface TimeDao {
    default void generatorCreateTime(Object entity) {
        if (entity instanceof HasTime) {
            HasTime timeEntity = (HasTime) entity;
            if (timeEntity.getCreateTime() == null) {
                timeEntity.setCreateTime(LocalDateTime.now());
            }
        }
    }

    default void updateModifyTime(Object entity) {
        if (entity instanceof HasTime) {
            HasTime timeEntity = (HasTime) entity;
            timeEntity.setModifyTime(LocalDateTime.now());
        }
    }
}
