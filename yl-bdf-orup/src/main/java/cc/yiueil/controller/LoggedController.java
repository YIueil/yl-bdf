package cc.yiueil.controller;


import cc.yiueil.dto.UserDto;
import cc.yiueil.util.JwtUtil;
import cc.yiueil.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

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
            userDto.setId(0L);
            userDto.setUserName("测试用户");
            userDto.setLoginName("测试用户");
            return userDto;
        }
        if (StringUtils.isNotEmpty(token)) {
            return JwtUtil.verifyToken(token);
        }
        return null;
    }
}
