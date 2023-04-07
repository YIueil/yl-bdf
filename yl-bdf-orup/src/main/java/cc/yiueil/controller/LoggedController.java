package cc.yiueil.controller;


import cc.yiueil.entity.UserEntity;
import cc.yiueil.lang.instance.User;
import cc.yiueil.session.SessionContent;
import cc.yiueil.util.CookieUtils;
import cc.yiueil.util.JWTUtil;
import cc.yiueil.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:YIueil
 * Date:2022/7/3 20:25
 * Description: 已登录的接口控制器
 */
public interface LoggedController extends BaseController {
    default UserEntity getUser(HttpServletRequest request){
        String token = CookieUtils.getCookieValue(request, "yl-token");
        if (StringUtils.isNotEmpty(token)) {
            return JWTUtil.verifyToken(token);
        }
        return null;
    }
}