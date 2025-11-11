package cc.yiueil.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

/**
 * CacheProperties 缓存配置类
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/11 23:23
 */
@Getter
@Setter
@Configuration
public class CacheProperties {
    private String cacheType;
}
