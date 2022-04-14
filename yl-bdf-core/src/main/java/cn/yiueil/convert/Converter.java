package cn.yiueil.convert;

import java.lang.reflect.Type;

public abstract class Converter<T> {
    public abstract T convert(Object obj, T defaultValue);

    /**
     * 获得转换器<br>自定义转换器优先
     *
     * @param type 类型
     * @return 转换器
     */
    public static Converter<?> getConvert(Type type) {
        return ConverterHolder.Registry.getInstance().getConverter(type);
    }
}
