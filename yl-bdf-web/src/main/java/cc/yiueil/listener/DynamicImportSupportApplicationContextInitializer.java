package cc.yiueil.listener;

import cc.yiueil.util.ParseUtils;
import cc.yiueil.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;

import javax.servlet.ServletContext;
import java.io.IOException;

/**
 * DynamicImportSupportApplicationContextInitializer 动态import标签功能上下文实现
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/12 0:22
 */
@Slf4j
public class DynamicImportSupportApplicationContextInitializer implements ApplicationContextInitializer<AbstractRefreshableWebApplicationContext> {
    public static final String PROPERTY_LOCATION_PARAM = "propertyConfig";

    @Override
    public void initialize(AbstractRefreshableWebApplicationContext applicationContext) {
        ResourcePropertySource propertySource;
        //从web.xml中的context-param中获取PROPERTY_LOCATION_PARAM参数
        ServletContext servletContext = applicationContext.getServletContext();
        if (servletContext == null) {
            return;
        }
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(resourceLoader);
        // 实际上配置一个通配符, 获取某个目录下的多个配置文件
        String propertyConfig = servletContext.getInitParameter(PROPERTY_LOCATION_PARAM);
        if (StringUtils.isNotBlank(propertyConfig)) {
            try {
                Resource[] resources = resolver.getResources(propertyConfig);
                for (Resource resource : resources) {
                    try {
                        propertySource = new ResourcePropertySource(ParseUtils.getString(resource.getFilename(), ""), resource);
                        //将读取的propertySource加载到全局（将会被优先使用）
                        applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);
                        log.info("property file {} has loaded", resource);
                    } catch (Exception e) {
                        log.warn("property file {} does not exists, ignored", resource);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}