package cn.yiueil.util;

import cn.yiueil.convert.ConverterHolder;

public class ParseUtils {
    /**
     * 通用转换方法，适用于自定义类型的转换，需要提前准备转换器
     * PS：内置的转换器的转换也可以用该方法实现
     * <p>ParseUtils.get(String.class, 1, null)</p>
     * @param classType 转换后的类型
     * @param o 需要转换的对象
     * @param defaultValue 转换失败的默认值
     * @param <T> 转换后的类型
     * @return 转换结果
     */
    public static <T> T get(Class<T> classType, Object o, T defaultValue) {
        return ConverterHolder.getConverter(classType).convert(o, defaultValue);
    }

    /**
     * 将对象转换获取为String类型
     * @param o 需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static String getString(Object o, String defaultValue) {
        return ConverterHolder.getConverter(String.class).convert(o, defaultValue);
    }

    /**
     * todo 方法还未开发 将对象转换获取为Integer类型
     * @param o 需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    @Deprecated
    public static Integer getInteger(Object o, Integer defaultValue) {
        return ConverterHolder.getConverter(Integer.class).convert(o, defaultValue);
    }
}
