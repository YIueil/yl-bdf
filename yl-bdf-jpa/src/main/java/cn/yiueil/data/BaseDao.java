package cn.yiueil.data;

import java.util.Optional;

/**
 * Author:YIueil
 * Date:2022/7/22 17:48
 * Description: 基础dao, 持有 save delete 基础功能
 */
public interface BaseDao {
    /**
     * 基本查询
     * @param <T> entity类型
     * @param tClass entity类型
     * @param id id
     * @return 查询结果
     */
    <T> Optional<T> findById(Class<T> tClass, Object id);

    /**
     * 基本保存、更新
     * @param entityObject entity对象
     */
    void save(Object entityObject);

    /**
     * 基本删除
     * @param entityObject entity对象
     */
    void delete(Object entityObject);

    /**
     * 基本删除
     * @param id id
     */
    @Deprecated
    <T> void deleteById(Class<T> tClass, Object id);

    /**
     * 通过guid进行删除
     * @param guid guid
     */
    <T> void deleteByGuid(Class<T> tClass, String guid);
}
