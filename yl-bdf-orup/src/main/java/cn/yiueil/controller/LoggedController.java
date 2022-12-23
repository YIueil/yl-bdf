package cn.yiueil.controller;


import cn.yiueil.lang.instance.User;
import cn.yiueil.session.SessionContent;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Author:YIueil
 * Date:2022/7/3 20:25
 * Description: 已登录的接口控制器
 */
public interface LoggedController extends BaseController {
    default User getUser(HttpServletRequest request){
        SessionContent sc = (SessionContent) request.getSession().getAttribute(SessionContent.SESSION_KEY);
        if (sc != null) {
            return sc.getUser();
        } else {
            return null;
        }
    }
}
