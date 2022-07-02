package cn.yiueil.util;

import cn.yiueil.convert.ConverterHolder;
import cn.yiueil.convert.impl.collection.ListConverter;
import cn.yiueil.lang.reflect.TypeReference;

import java.util.*;

public class ParseUtils {
    /**
     * 通用转换方法，适用于自定义类型的转换，需要提前准备转换器
     * PS：内置的转换器的转换也可以用该方法实现
     * <p>ParseUtils.get(String.class, 1, null)</p>
     *
     * @param typeReference 转换后的类型
     * @param o             需要转换的对象
     * @param defaultValue  转换失败的默认值
     * @param <T>           转换后的类型
     * @return 转换结果
     */
    public static <T> T get(TypeReference<T> typeReference, Object o, T defaultValue) {
        return ConverterHolder.getConverter(typeReference).convert(o, defaultValue);
    }

    /**
     * 将对象转换获取为String类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static String getString(Object o, String defaultValue) {
        return get(new TypeReference<String>() {
        }, o, defaultValue);
    }

    /**
     * 将对象转换获取为String类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static Integer getInteger(Object o, Integer defaultValue) {
        return get(new TypeReference<Integer>() {
        }, o, defaultValue);
    }

    /**
     * 将对象转换获取为Float类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static Float getFloat(Object o, Float defaultValue) {
        return get(new TypeReference<Float>() {
        }, o, defaultValue);
    }

    /**
     * 将对象转换获取为Double类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static Double getDouble(Object o, Double defaultValue) {
        return get(new TypeReference<Double>() {
        }, o, defaultValue);
    }

    /**
     * 将对象转换获取为Long类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static Long getLong(Object o, Long defaultValue) {
        return get(new TypeReference<Long>() {
        }, o, defaultValue);
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
        return get(new TypeReference<Boolean>() {
        }, o, defaultValue);
    }

    /**
     * 将对象转换获取为List类型
     *
     * @param classType 转换后的类型
     * @param o         转换前的对象
     * @param <T>       转换后的元素类型
     * @return 转换后的集合
     */
    public static <T> List<T> getList(Object o , Class<T> classType) {
        ListConverter<T> listConverter = new ListConverter<T>(TypeReference.of(classType));
        return listConverter.convert(o, Collections.emptyList());
    }

}
