package cc.yiueil.util;

/**
 * Author:YIueil
 * Date:2022/7/4 22:24
 * Description: todo java对象工具类
 */
public class ObjectUtils {
    /**
     * 获取对象, 如果传入的对象为空, 则返回使用默认值
     * @param t 传入的对象
     * @param defaultValue 传入对象为空时提供的默认值
     * @param <T> 参数类型
     * @return 传入对象为空时返回 t, 否则返回 defaultValue
     */
    public static <T> T defaultIfNull(T t, T defaultValue) {
        return t != null ? t : defaultValue;
    }
}
