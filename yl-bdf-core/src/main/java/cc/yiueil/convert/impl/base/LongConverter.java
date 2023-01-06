package cc.yiueil.convert.impl.base;

import cc.yiueil.convert.Converter;

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
