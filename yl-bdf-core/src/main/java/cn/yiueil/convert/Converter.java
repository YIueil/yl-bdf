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
    /**
     * 转换 默认不忽略转换错误
     *
     * @param obj          需转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    T convert(Object obj, T defaultValue);

    /**
     * 转换时是否需要忽略本次转换错误
     *
     * @param obj          需转换的对象
     * @param defaultValue 默认值
     * @param isCheck      是否忽略转换错误
     * @return 转换结果
     */
    default T convert(Object obj, T defaultValue, boolean isCheck) {
        if (isCheck) {
            return convert(obj, defaultValue);
        } else {
            try {
                return convert(obj, defaultValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }
}
