package cc.yiueil.controller;

import cc.yiueil.constant.ORUPRestURL;
import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.entity.UserEntity;
import cc.yiueil.exception.ResourceNotFoundException;
import cc.yiueil.general.RestURL;
import cc.yiueil.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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

    /**
     * 用户登入
     * @param user 用户登入录入信息
     * @return 登入结果, 成功则返回 token
     */
    @PostMapping(value = "login")
    public String login(@RequestBody UserEntity user) {
        UserEntity userEntity = baseDao.findById(UserEntity.class, user.getId()).orElseThrow(ResourceNotFoundException::new);
        if (userEntity.getPassword().equals(user.getPassword())) {
            return success(JWTUtil.generateToken(userEntity), "登录成功");
        }
        return fail("登录失败, 账号或者密码错误");
    }

    /**
     * 用户登出
     * @param user 用户信息
     * @return 登出结果
     */
    @PostMapping(value = "logout")
    public String logout(@RequestBody UserEntity user) {
        return null;
    }

    /**
     * 获取当前用户信息
     * @param request 请求头
     * @return 获取到用户信息, 未登录则返回游客用户
     */
    @GetMapping(value = "currentUser")
    public String currentUser(HttpServletRequest request) {
        return success(Optional.ofNullable(getUser(request)).orElseGet(() -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName("游客");
            return userEntity;
        }));
    }
}
