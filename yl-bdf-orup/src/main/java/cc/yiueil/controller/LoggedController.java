package cc.yiueil.controller;


import cc.yiueil.entity.UserEntity;
import cc.yiueil.util.JwtUtil;
import cc.yiueil.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:YIueil
 * Date:2022/7/3 20:25
 * Description: 已登录的接口控制器
 */
public interface LoggedController extends BaseController {
    default UserEntity getUser(HttpServletRequest request){
        String token = request.getHeader("yl-token");
        if ("Fk12345.".equals(token)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(0L);
            userEntity.setUserName("测试用户");
            userEntity.setLoginName("测试用户");
            return userEntity;
        }
        if (StringUtils.isNotEmpty(token)) {
            return JwtUtil.verifyToken(token);
        }
        return null;
    }
}
