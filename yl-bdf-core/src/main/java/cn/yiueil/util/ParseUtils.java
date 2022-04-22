package cn.yiueil.util;

import cn.yiueil.convert.ConverterHolder;
import cn.yiueil.lang.TypeReference;

import java.lang.reflect.Type;
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
        return ConverterHolder.convert(typeReference, o, defaultValue);
    }

    /**
     * 将对象转换获取为String类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static String getString(Object o, String defaultValue) {
        return ConverterHolder.convert(TypeReference.of(String.class), o, defaultValue);
    }

    /**
     * 将对象转换获取为String类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static Integer getInteger(Object o, Integer defaultValue) {
        return ConverterHolder.convert(TypeReference.of(Integer.class), o, defaultValue);
    }

    /**
     * 将对象转换获取为Float类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static Float getFloat(Object o, Float defaultValue) {
        return ConverterHolder.convert(TypeReference.of(Float.class), o, defaultValue);
    }

    /**
     * 将对象转换获取为Double类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static Double getDouble(Object o, Double defaultValue) {
        return ConverterHolder.convert(TypeReference.of(Double.class), o, defaultValue);
    }

    /**
     * 将对象转换获取为Long类型
     *
     * @param o            需要转换的对象
     * @param defaultValue 默认值
     * @return 转换结果
     */
    public static Long getLong(Object o, Long defaultValue) {
        return ConverterHolder.convert(TypeReference.of(Long.class), o, defaultValue);
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
        return ConverterHolder.convert(TypeReference.of(Boolean.class), o, defaultValue);
    }

    /**
     * todo list类型的转换功力不够
     *
     * @param classType 转换后的类型
     * @param o         转换前的对象
     * @param <T>       转换后的元素类型
     * @return 转换后的集合
     */
    public static <T> List<T> getList(Class<T> classType, Object o) {
        TypeReference<T> typeReference = TypeReference.of(classType);
        return getList(typeReference, o);
    }

    @SuppressWarnings("all")
    private static <T> List<T> getList(TypeReference<T> typeReference, Object o) {
        List<T> result = new ArrayList<>();
        if (o instanceof Iterable) {
            Iterable iterable = (Iterable) o;
            for (Object value : iterable) {
                result.add(ConverterHolder.convert(typeReference, value, null));
            }
            return result;
        }
        if (o instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) o;
            String s = charSequence.toString();
            if (s.contains(",")) {
                return (List<T>) Arrays.asList(s.split(","));
            }
        }
        return Collections.emptyList();
    }

    /**
     * todo 直接适配集合的转换
     */
    private static <T> Collection<T> getCollection(Type collectionType, Type elementType, Object value) {
        return null;
    }
}
