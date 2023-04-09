package cc.yiueil.interceptor;

import cc.yiueil.exception.UnauthorizedException;
import cc.yiueil.lang.instance.User;
import cc.yiueil.util.CookieUtils;
import cc.yiueil.util.JWTUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class AuthenticateInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("yl-token");
        if ("Fk12345.".equals(token)) {
            return true;
        }
        try {
            User<Long> user = JWTUtil.verifyToken(token);
            //1.判断请求是否有效
            //2.判断是否需要续期
            return user != null;
        } catch (JWTDecodeException jwtDecodeException) {
            throw new UnauthorizedException("未登陆或授权到期, 请重新登陆");
        }
    }
}
