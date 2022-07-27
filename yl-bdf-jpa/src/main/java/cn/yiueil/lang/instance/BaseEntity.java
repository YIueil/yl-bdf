package cn.yiueil.lang.instance;

/**
 * Author:YIueil
 * Date:2022/7/28 1:40
 * Description: 基本实体 包括id, guid, createTime, modifyTime, createUser属性
 * @param <T> 主键类型
 */
public interface BaseEntity<T> extends HasId<T>, HasGuid, HasTime, HasOwn<T> {
}
