package cn.yiueil.convert;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Author:YIueil
 * Date:2022/4/22 16:11
 * Description: todo 等级不够写这种处理器
 */
public abstract class CollectionConverter<T> implements Converter<T>{
    @Override
    public T convert(Object obj, T defaultValue) {
        if (obj instanceof Collection) {
            return convertInternal(obj, getItemType());
        }
        return null;
    }

    protected abstract T convertInternal(Object obj, Type type);

    protected abstract Type getItemType();
}
