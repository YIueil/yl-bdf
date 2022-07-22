package cn.yiueil.data;

import cn.yiueil.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Author:YIueil
 * Date:2022/7/22 17:59
 * Description: 批处理dao,持有 批量保存,批量删除 功能
 */
public interface BatchDao extends BaseDao{
    /**
     * 批量保存
     * @param tClass 批量保存entity类型
     * @param entityList entity集合
     * @param <T> entity 类型
     * @return 批量保存结果
     */
    default <T> List<T> batchSave(Class<T> tClass, Collection<T> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(entityList);
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
