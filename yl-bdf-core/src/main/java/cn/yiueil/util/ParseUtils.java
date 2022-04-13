package cn.yiueil.util;

public class ParseUtils {

    //region getString
    public static String getStringDefaultEmptyStr(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String getStringDefaultNull(Object obj) {
        return obj == null ? null : obj.toString();
    }

    public static String getString(Object obj, String defaultStr) {
        return obj == null ? defaultStr : obj.toString();
    }
    //endregion


}
