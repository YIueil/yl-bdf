package cc.yiueil.interceptor;

import cc.yiueil.lang.instance.User;
import cc.yiueil.util.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticateInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authToken = request.getHeader("token");
        User user = JWTUtil.verifyToken(authToken);
        if (user != null) {
            return true;
        } else {
            return false;
        }
        //1.判断请求是否有效

        //2.判断是否需要续期
    }
}
