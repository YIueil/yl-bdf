package cn.yiueil.convert;

/**
 * Author:YIueil
 * Date:2022/4/15 15:13
 * Description:
 */
public interface Converter<T> {
    T convert(Object obj, T defaultValue);
}
