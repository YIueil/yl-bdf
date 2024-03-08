package cc.yiueil.redis;

import cc.yiueil.api.Cache;

/**
 * RedisCache redis缓存
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/9 1:30
 */
public class RedisCache implements Cache {
    @Override
    public void set(String key, Object value) {

    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public void delete(String key) {

    }
}
