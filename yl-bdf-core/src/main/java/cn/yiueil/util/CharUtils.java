package cn.yiueil.util;

public class CharUtils {
    /**
     * 判断是否为空占位字符,包括空格、换行、制表符
     * @param c 待判断字符
     * @return 判定结果
     */
    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c == '\u202a'
                || c == '\u0000';
    }
}
