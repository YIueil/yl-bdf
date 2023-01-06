package cc.yiueil.lang.instance;

/**
 * Author:YIueil
 * Date:2022/7/4 23:47
 * Description: 转换器门面 定义两个不同类的转换规则
 * @param <T> 转换前的类
 * @param <E> 转换后的类
 */
@FunctionalInterface
public interface Parser<T, E> {
    void parse(T t, E e);
}
