package cn.yiueil.convert;

import cn.yiueil.convert.impl.*;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/4/13 11:34
 * Description: 类型转换器Holder
 */
public class ConverterHolder {
    private Map<Type, Converter<?>> defaultConverterMap;
    private Map<Type, Converter<?>> customConverterMap;

    private static class SingletonRegistry { // todo 这部分写法列为自己的代码规范
        private static final ConverterHolder INSTANCE = new ConverterHolder();
    }

    public static ConverterHolder getInstance() {
        return SingletonRegistry.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    private static <T> Converter<T> getDefaultConverter(Class<T> classType) {
        return (getInstance().defaultConverterMap == null || getInstance().defaultConverterMap.size() == 0) ? null : (Converter<T>)getInstance().defaultConverterMap.get(classType);
    }

    @SuppressWarnings("unchecked")
    private static <T> Converter<T> getCustomConverter(Class<T> classType) {
        return (getInstance().customConverterMap == null || getInstance().customConverterMap.size() == 0) ? null : (Converter<T>)getInstance().customConverterMap.get(classType);
    }

    /**
     * 获取转换器 自定义转换器优先
     * @param classType 转换后的实际类型
     * @param <T> 实际类型
     * @return 转换器
     */
    public static <T> Converter<T> getConverter(Class<T> classType) {
        Converter<T> converter;
        converter = getCustomConverter(classType);
        if (converter == null) {
            converter = getDefaultConverter(classType);
        }
        if (converter == null) {
            throw new RuntimeException("G! 没有找到转换器");
        }
        return converter;
    }

    private ConverterHolder() {
        loadDefaultConverter();
        loadCustomConverter();
    }

    private void loadCustomConverter() {
        defaultConverterMap = new HashMap<>();
        defaultConverterMap.put(String.class, new StringConverter());
        defaultConverterMap.put(Date.class, new DateConverter());
        defaultConverterMap.put(Integer.class, new IntegerConverter());
        defaultConverterMap.put(FloatConverter.class, new FloatConverter());
        defaultConverterMap.put(Double.class, new DoubleConverter());
        defaultConverterMap.put(Long.class, new LongConverter());
        defaultConverterMap.put(Boolean.class, new BooleanConverter());
    }

    private void loadDefaultConverter() {
        customConverterMap = new HashMap<>();
        // todo 添加对于自定义转换器的扫描添加支持
    }
}
