package cc.yiueil.memory;

import cc.yiueil.api.CacheService;

import java.util.HashMap;
import java.util.Map;

/**
 * MemoryCache 内存缓存
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/9 1:27
 */
public class MemoryCacheImpl implements CacheService {
    private final Map<String, Object> CACHE_MAP = new HashMap<>();

    @Override
    public void set(String key, Object value) {
        CACHE_MAP.put(key, value);
    }

    @Override
    public Object get(String key) {
        return CACHE_MAP.get(key);
    }

    @Override
    public void delete(String key) {
        CACHE_MAP.remove(key);
    }
}
