package cc.yiueil.api;

/**
 * Cache 基础缓存接口
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/9 1:17
 */
public interface Cache {
    /**
     * 设置值
     * @param key 键
     * @param value 值
     */
    void set(String key, Object value);

    /**
     * 获取值
     * @param key 键
     * @return 值
     */
    Object get(String key);

    /**
     * 删除键值对
     * @param key 键
     */
    void delete(String key);
}
