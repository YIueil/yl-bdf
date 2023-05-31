package cc.yiueil.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringContextUtils spring容器工具类
 * @author 弋孓 YIueil@163.com
 * @date 2022/7/26 0:10
 * @version 1.0
 */
@Slf4j
@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanId) {
        if (applicationContext.containsBean(beanId)) {
            return (T) applicationContext.getBean(beanId);
        } else {
            log.error(String.format("not find bean : %s", beanId));
            return null;
        }
    }

    public static <T> T getBean(Class<T> cls) {
        return applicationContext.getBean(cls);
    }
}
