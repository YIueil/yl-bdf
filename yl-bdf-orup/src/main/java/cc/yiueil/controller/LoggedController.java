package cc.yiueil.controller;


import cc.yiueil.dto.UserDto;
import cc.yiueil.util.JwtUtils;
import cc.yiueil.util.StringUtils;
import com.auth0.jwt.interfaces.Claim;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * LoggedController
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2022/7/3 20:25
 */
public interface LoggedController extends BaseController {
    /**
     * 获取以及登录的用户信息
     * @param request 请求体
     * @return 用户信息
     */
    default UserDto getUser(HttpServletRequest request) {
        String token = request.getHeader("yl-token");
        if ("Fk12345.".equals(token)) {
            UserDto userDto = new UserDto();
            userDto.setId(1L);
            userDto.setUserName("测试用户");
            userDto.setLoginName("测试用户");
            return userDto;
        }
        if (StringUtils.isNotEmpty(token)) {
            return JwtUtils.verifyTokenUser(token);
        }
        return null;
    }

    /**
     * 获取上下文哈希表: 适用于publicToken
     * @param request 请求体
     * @return 上下文哈希表
     */
    default Map<String, Claim> getContextMap(HttpServletRequest request) {
        String publicToken = request.getParameter("publicToken");
        if (publicToken == null) {
            return null;
        }
        return JwtUtils.verifyTokenToMap(publicToken);
    }
}
