package cn.yiueil.convert.impl;

import cn.yiueil.convert.Converter;

public class LongConverter implements Converter<Long> {
    @Override
    public Long convert(Object obj, Long defaultValue) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        if (obj instanceof CharSequence) {
            return Long.parseLong(obj.toString());
        }
        return defaultValue;
    }
}
