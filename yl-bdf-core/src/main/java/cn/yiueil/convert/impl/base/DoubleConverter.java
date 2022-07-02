package cn.yiueil.convert.impl.base;

import cn.yiueil.convert.Converter;

public class DoubleConverter implements Converter<Double> {
    @Override
    public Double convert(Object obj, Double defaultValue) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        if (obj instanceof CharSequence) {
            return Double.parseDouble(obj.toString());
        }
        return defaultValue;
    }
}
