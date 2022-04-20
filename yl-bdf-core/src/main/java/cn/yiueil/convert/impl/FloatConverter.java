package cn.yiueil.convert.impl;

import cn.yiueil.convert.Converter;

public class FloatConverter implements Converter<Float> {
    @Override
    public Float convert(Object obj, Float defaultValue) {
        if (obj instanceof Number) {
            return ((Number) obj).floatValue();
        }
        if (obj instanceof CharSequence) {
            return Float.parseFloat(obj.toString());
        }
        return defaultValue;
    }
}
