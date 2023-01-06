package cc.yiueil.util;

import java.util.Collection;

/**
 * Author:YIueil
 * Date:2022/7/22 18:09
 * Description: 集合工具类
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection<?> collection) {
        return collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}
