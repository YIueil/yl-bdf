package cn.yiueil.util;

import cn.yiueil.lang.regex.PatternPool;

import java.util.regex.Pattern;

/**
 * Author:YIueil
 * Date:2022/4/22 17:14
 * Description: 验证工具类
 * @see PatternPool
 */
public class CheckUtils {
    public static boolean isPhone(String str) {
        return validator(PatternPool.PHONE, str);
    }

    public static boolean isMail(String str) {
        return validator(PatternPool.EMAIL, str);
    }

    public static boolean isIDCard(String str) {
        return validator(PatternPool.ID_CARD, str);
    }

    private static boolean validator(Pattern pattern, String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return pattern.matcher(str).matches();
    }
}
