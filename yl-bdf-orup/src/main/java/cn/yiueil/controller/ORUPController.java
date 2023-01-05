package cn.yiueil.controller;

import cn.yiueil.constant.ORUPRestURL;
import cn.yiueil.data.impl.JpaBaseDao;
import cn.yiueil.exception.ResourceNotFoundException;
import cn.yiueil.general.RestURL;
import cn.yiueil.lang.instance.User;
import cn.yiueil.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    JpaBaseDao baseDao;

    @PostMapping(value = "login")
    public String login(User user) {
        return JWTUtil.generateToken(baseDao.findById(User.class, user.getId()).orElseThrow(ResourceNotFoundException::new));
    }

    @PostMapping(value = "logout")
    public String logout(User user) {
        return null;
    }

    @GetMapping(value = "currentUser")
    public String currentUser(HttpServletRequest request) {
        HashMap<String, Object> user = new HashMap<>();
        user.put("id", -1);
        user.put("name", "游客");
        return success(getUser(request) == null ? user : getUser(request));
    }
}
