package cn.yiueil.controller;

import cn.yiueil.general.RestURL;
import cn.yiueil.constant.ORUPRestURL;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Author:YIueil
 * Date:2022/11/15 22:23
 * Description: 用户账户控制器
 */
@CrossOrigin
@RestController
@RequestMapping(value = RestURL.BASE_PATH + ORUPRestURL.ORUP)
public class ORUPController implements LoggedController{
    @GetMapping(value = "currentUser")
    public String currentUser(HttpServletRequest request) {
        HashMap<String, Object> user = new HashMap<>();
        user.put("id", 1);
        user.put("name", "张三");
        return success(getUser(request) == null ? user : getUser(request));
    }
}
