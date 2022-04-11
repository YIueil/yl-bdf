package cn.yiueil.util;

public class ParseUtils {
    public static String getStringDefaultEmptyStr(Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof CharSequence) {
            return value.toString();
        }
        return "";
    }

    public static String getStringDefaultNull(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof CharSequence) {
            return value.toString();
        }
        return null;
    }

    public static String getString(Object value, String defaultStr) {
        if (value == null) {
            return defaultStr;
        } else if (value instanceof CharSequence) {
            return value.toString();
        }
        return defaultStr;
    }

}
