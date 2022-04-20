package cn.yiueil.util;

import cn.yiueil.convert.ConverterHolder;
import cn.yiueil.lang.TypeRef;

import java.util.List;

public class ParseUtils {
    /**
     * 通用转换方法，适用于自定义类型的转换，需要提前准备转换器
     * PS：内置的转换器的转换也可以用该方法实现
     * <p>ParseUtils.get(String.class, 1, null)</p>
     *
     * @param ref    转换后的类型
     * @param o            需要转换的对象
     * @param defaultValue 转换失败的默认值
     * @param <T>          转换后的类型
     * @return 转换结果
     */
    public static <T> T get(TypeRef<T> ref, Object o, T defaultValue) {
        return ConverterHolder.getConverter(ref).convert(o, defaultValue);
    }

    /**
     * 将对象转换获取为String类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static String getString(Object o, String defaultValue) {
        return ConverterHolder.getConverter(new TypeRef<String>() {
        }).convert(o, defaultValue);
    }

    /**
     * 将对象转换获取为String类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static Integer getInteger(Object o, Integer defaultValue) {
        return ConverterHolder.getConverter(new TypeRef<Integer>() {
        }).convert(o, defaultValue);
    }

    /**
     * 将对象转换获取为Float类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static Float getFloat(Object o, Float defaultValue) {
        return ConverterHolder.getConverter(new TypeRef<Float>() {
        }).convert(o, defaultValue);
    }

    /**
     * 将对象转换获取为Double类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static Double getDouble(Object o, Double defaultValue) {
        return ConverterHolder.getConverter(new TypeRef<Double>() {
        }).convert(o, defaultValue);
    }

    /**
     * 将对象转换获取为Long类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static Long getLong(Object o, Long defaultValue) {
        return ConverterHolder.getConverter(new TypeRef<Long>() {
        }).convert(o, defaultValue);
    }

    /**
     * 将对象转换获取为Boolean类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     * <pre>
     *     e.g.:<br>
     *     ParseUtils.getBoolean("true", null) => true<br>
     *     ParseUtils.getBoolean("false", null) => false<br>
     *     ParseUtils.getBoolean(1, null) => true<br>
     *     ParseUtils.getBoolean(0, null) => false<br>
     *     ParseUtils.getBoolean(-1, null) => false<br>
     *     ParseUtils.getBoolean("1", null) => false<br>
     * </pre>
     */
    public static Boolean getBoolean(Object o, Boolean defaultValue) {
        return ConverterHolder.getConverter(new TypeRef<Boolean>() {
        }).convert(o, defaultValue);
    }

    // todo this
    public static <T> List<T> getList(TypeRef<T> typeRef, Object o) {
        return null;
    }

}
