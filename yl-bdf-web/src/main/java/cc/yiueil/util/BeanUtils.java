package cc.yiueil.util;

import org.springframework.beans.BeansException;

/**
 * BeanUtils
 * 主要针对简单类型转换进行返回值封装增强
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/9/10 0:50
 */
public class BeanUtils {
    public static <T> T copyProperties(Object source, T target) throws BeansException {
        org.springframework.beans.BeanUtils.copyProperties(source, target, null, null);
        return target;
    }

    public static <T> T copyProperties(Object source, T target, String... ignoreProperties) throws BeansException {
        org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
        return target;
    }
}
