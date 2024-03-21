package cc.yiueil.interceptor;

import cc.yiueil.annotation.VerifyToken;
import cc.yiueil.controller.LoggedController;
import cc.yiueil.dto.UserDto;
import cc.yiueil.exception.UnauthorizedException;
import cc.yiueil.util.GlobalProperties;
import cc.yiueil.util.JwtUtils;
import cc.yiueil.util.ParseUtils;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 应用权限检测拦截器
 * @author 弋孓 YIueil@163.com
 * @date 2023/5/30 22:17
 * @version 1.0
 */
public class AuthenticateInterceptor implements HandlerInterceptor {
    /**
     *
     * @param request 请求体
     * @param response 响应体
     * @param handler 处理器
     * @return 是否通过拦截器
     */
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.hasMethodAnnotation(VerifyToken.class)) {
                String publicToken = ParseUtils.getString(request.getParameter("publicToken"), null);
                Map<String, Claim> stringClaimMap = verifyPublicToken(publicToken);
                return stringClaimMap != null;
            } else {
                // 获取Controller类信息
                Object bean = handlerMethod.getBean();
                if (bean instanceof LoggedController) {
                    return verifyToken(request);
                }
            }
        }
        return true;
    }

    /**
     * publicToken校验
     * publicToken 未登录客户端可通过publicToken来实现的接口请求
     * @param publicToken publicToken
     * @return 校验结果
     */
    private Map<String, Claim> verifyPublicToken(String publicToken) {
        return JwtUtils.verifyTokenToMap(publicToken);
    }

    /**
     * token校验
     * @param request 请求体
     * @return 校验结果
     */
    private boolean verifyToken(HttpServletRequest request) {
        String token = request.getHeader("yl-token");
        // todo 使用添加全局properties
        if (GlobalProperties.getProperties("application.public.token", "Fk12345.").equals(token)) {
            return true;
        }
        try {
            UserDto userDto = JwtUtils.verifyToken(token);
            //1.判断请求是否有效
            //2.判断是否需要续期
            return userDto != null;
        } catch (JWTDecodeException jwtDecodeException) {
            throw new UnauthorizedException("身份校验未通过, 请重新登陆");
        } catch (TokenExpiredException tokenExpiredException) {
            throw new UnauthorizedException("未登陆或授权到期, 请重新登陆");
        }
    }
}
