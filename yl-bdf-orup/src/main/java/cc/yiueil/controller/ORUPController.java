package cc.yiueil.controller;

import cc.yiueil.constant.ORUPRestURL;
import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.entity.UserEntity;
import cc.yiueil.exception.ResourceNotFoundException;
import cc.yiueil.general.RestURL;
import cc.yiueil.repository.UserRepository;
import cc.yiueil.service.ORUPService;
import cc.yiueil.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    ORUPService orupService;

    /**
     * 用户登入
     *
     * @param loginName 登陆名
     * @param password 密码
     * @param response 响应体
     * @return 登入结果, 成功则返回 token
     */
    @PostMapping(value = "login")
    public String login(@RequestParam String loginName,
                        @RequestParam String password,
                        HttpServletResponse response
    ) {
        UserEntity userEntity = userRepository.findUserEntityByLoginName(loginName).orElse(new UserEntity());
        if (password.equals(userEntity.getPassword())) {
            String token = JWTUtil.generateToken(userEntity);
            return success(token, "登录成功");
        }
        return fail("登录失败, 账号或者密码错误");
    }

    /**
     * 用户注册
     * @param registerUser 用户信息
     * @return 新注册用户信息
     */
    @PostMapping(value = "register")
    public String register(@RequestBody UserEntity registerUser) {
        try {
            UserEntity userEntity = orupService.registerUser(registerUser);
            return success(userEntity);
        } catch (Exception e) {
            return fail("用户登陆名已存在");
        }
    }

    /**
     * 用户登出
     * @param response 响应体
     * @return 登出结果
     */
    @PostMapping(value = "logout")
    public String logout(HttpServletResponse response) {
        return success();
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
