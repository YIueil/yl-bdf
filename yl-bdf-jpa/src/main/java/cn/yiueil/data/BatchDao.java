package cn.yiueil.data;

import cn.yiueil.util.CollectionUtils;

import java.util.*;

/**
 * Author:YIueil
 * Date:2022/7/22 17:59
 * Description: 批处理dao,持有 批量保存,批量删除 功能
 */
public interface BatchDao extends BaseDao{
    /**
     * 批量保存
     * @param entityList entity集合
     * @param <T> entity 类型
     */
    default <T> void batchSave(Collection<T> entityList) {
        for (T next : entityList) {
            save(next);
        }
    }

    /**
     * 批量删除
     * @param entityList entity集合
     */
    default void batchDelete(List<?> entityList) {
        for (Object object : entityList) {
            delete(object);
        }
    }
}
