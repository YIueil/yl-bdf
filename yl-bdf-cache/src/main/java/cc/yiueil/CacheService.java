package cc.yiueil;

import cc.yiueil.api.Cache;
import lombok.Getter;
import lombok.Setter;

/**
 * CacheInitializer 缓存初始化类
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/9 1:43
 */
@Getter
@Setter
public class CacheService {

    private Cache cache;

    /**
     * 设置值
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value) {
        cache.set(key, value);
    }

    /**
     * 获取值
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return cache.get(key);
    }

    /**
     * 删除键值对
     * @param key 键
     */
    public void delete(String key) {
        cache.delete(key);
    }
}
