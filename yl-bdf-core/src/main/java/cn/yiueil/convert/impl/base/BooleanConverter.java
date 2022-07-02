package cn.yiueil.convert.impl.base;

import cn.yiueil.convert.Converter;

public class BooleanConverter implements Converter<Boolean> {
    @Override
    public Boolean convert(Object obj, Boolean defaultValue) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue() > 0;
        }
        if (obj instanceof CharSequence) {
            return Boolean.parseBoolean(obj.toString());
        }
        return defaultValue;
    }
}
