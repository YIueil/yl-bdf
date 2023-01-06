package cc.yiueil.util;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/7/4 23:33
 * Description: todo map工具类
 */
public class MapUtils {
    /**
     * Map是否为空
     *
     * @param map 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    /**
     * Map是否不为空
     *
     * @param map 集合
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }


    /**
     * 按照值排序，可选是否倒序
     *
     * @param map    需要对值排序的map
     * @param <K>    键类型
     * @param <V>    值类型
     * @param isDesc 是否倒序
     * @return 排序后新的Map
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = new LinkedHashMap<>();
        Comparator<Map.Entry<K, V>> entryComparator = Map.Entry.comparingByValue();
        if (isDesc) {
            entryComparator = entryComparator.reversed();
        }
        map.entrySet().stream().sorted(entryComparator).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }
}
