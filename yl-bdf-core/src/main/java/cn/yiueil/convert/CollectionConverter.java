package cn.yiueil.convert;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Author:YIueil
 * Date:2022/4/22 16:11
 * Description: 集合类型转换
 */
public abstract class CollectionConverter<T> implements Converter<T>{
    @Override
    public T convert(Object obj, T defaultValue) {
        return convertInternal(obj);
    }

    protected abstract T convertInternal(Object obj);
}
