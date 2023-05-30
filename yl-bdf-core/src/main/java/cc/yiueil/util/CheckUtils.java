package cc.yiueil.util;

import cc.yiueil.lang.regex.PatternPool;

import java.util.regex.Pattern;

/**
 * CheckUtils 字符串验证工具类
 * @see PatternPool
 * @author 弋孓 YIueil@163.com
 * @date 2023/5/30 23:14
 * @version 1.0
 */
public class CheckUtils {
    public static boolean isPhone(String str) {
        return validator(PatternPool.PHONE, str);
    }

    public static boolean isEmail(String str) {
        return validator(PatternPool.EMAIL, str);
    }

    public static boolean isIdCard(String str) {
        return validator(PatternPool.ID_CARD, str);
    }

    private static boolean validator(Pattern pattern, String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return pattern.matcher(str).matches();
    }
}
