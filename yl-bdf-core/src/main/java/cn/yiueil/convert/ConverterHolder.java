package cn.yiueil.convert;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/4/13 11:34
 * Description: 类型转换器Holder
 */
public class ConverterHolder {
    private ConverterHolder() {
        loadDefaultConverter();
        loadCustomConverter();
    }

    /**
     * 默认类型转换器
     */
    private Map<Type, Converter<?>> defaultConverterMap;
    /**
     * 用户自定义类型转换器
     */
    private volatile Map<Type, Converter<?>> customConverterMap;

    private void loadCustomConverter() {
        //todo 支持自定义的类型转换器
        customConverterMap = new HashMap<>();
    }

    private void loadDefaultConverter() {
        defaultConverterMap = new HashMap<>();
        defaultConverterMap.put(String.class, new StringConverter());
    }

    public <T> Converter<T> getConverter(Type type) {
        return null;
    }

    public static class Registry {
        public static ConverterHolder getInstance() {
            return new ConverterHolder();
        }
    }
}
