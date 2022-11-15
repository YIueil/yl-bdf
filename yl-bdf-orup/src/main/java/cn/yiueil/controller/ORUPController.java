package cn.yiueil.controller;

import cn.yiueil.general.RestURL;
import cn.yiueil.constant.ORUPRestURL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:YIueil
 * Date:2022/11/15 22:23
 * Description: 用户账户控制器
 */
@RestController(value = RestURL.BASE_PATH + ORUPRestURL.ORUP)
public class ORUPController implements LoggedController{
    @GetMapping(value = "currentUser")
    public String currentUser(HttpServletRequest request) {
        return success(getUser(request));
    }
}
