package cc.yiueil.convert.impl.base;

import cc.yiueil.convert.Converter;

public class IntegerConverter implements Converter<Integer> {
    @Override
    public Integer convert(Object obj, Integer defaultValue) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (obj instanceof CharSequence) {
            return Integer.parseInt(obj.toString());
        }
        return defaultValue;
    }
}
