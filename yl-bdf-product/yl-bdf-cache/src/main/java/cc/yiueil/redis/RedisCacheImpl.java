package cc.yiueil.redis;

import cc.yiueil.api.CacheService;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * RedisCache redis缓存
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/9 1:30
 */
public class RedisCacheImpl implements CacheService {

    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
