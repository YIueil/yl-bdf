package cn.yiueil.convert;

public class StringConverter extends Converter<String> {
    @Override
    public String convert(Object obj, String defaultValue) {
        return obj == null ? defaultValue : obj.toString();
    }
}
