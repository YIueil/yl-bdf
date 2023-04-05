package cc.yiueil.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Author:YIueil
 * Date:2023/4/5 22:55
 * Description: cookie工具类
 */
public final class CookieUtils {

    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } else {
                        retValue = cookie.getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(cookie.getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
        	 e.printStackTrace();
        }
        return retValue;
    }
}

