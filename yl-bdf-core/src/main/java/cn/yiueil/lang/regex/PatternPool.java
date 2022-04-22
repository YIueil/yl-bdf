package cn.yiueil.lang.regex;

import java.util.regex.Pattern;

/**
 * Author:YIueil
 * Date:2022/4/22 17:12
 * Description: 正则资源池
 */
public class PatternPool {
    public static final Pattern PHONE = Pattern.compile(RegexPool.PHONE);
    public static final Pattern EMAIL = Pattern.compile(RegexPool.EMAIL);
    public static final Pattern ID_CARD = Pattern.compile(RegexPool.ID_CARD);
}
