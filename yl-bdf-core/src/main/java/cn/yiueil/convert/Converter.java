package cn.yiueil.convert;

/**
 * Author:YIueil
 * Date:2022/4/15 15:13
 * Description: 转换器接口
 * <pre>
 *     转换器添加步骤：
 *     1、添加接口实现
 *     2、在ConverterHolder中添加对应的转换器实例
 * </pre>
 */
public interface Converter<T> {
    T convert(Object obj, T defaultValue);
}
