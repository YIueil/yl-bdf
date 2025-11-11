package cc.yiueil.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig
 * todo 这个配置类似乎并没有生效, 当使用<mvc:annotation-driven />注解驱动时，继承`WebMvcConfigurer`类来实现静态处理器将不会起作用。因为`<mvc:annotation-driven />`注解驱动使用的是基于老版本`WebMvcConfigurationSupport`来实现的。若一定要用`WebMvcConfigurer`，需要自行取得MVC相关的控制权，添加`@EnableWebMvc`。
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/5/31 23:28
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 静态资源放行
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");

        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html", "doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
