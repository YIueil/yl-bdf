package cc.yiueil.controller;

import cc.yiueil.constant.OrupRestURL;
import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.dto.UserDTO;
import cc.yiueil.entity.UserEntity;
import cc.yiueil.general.RestUrl;
import cc.yiueil.repository.UserRepository;
import cc.yiueil.service.OrupService;
import cc.yiueil.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 用户账户控制器
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/5/30 22:16
 */
@Api(value = "ORUP-用户角色账户权限控制")
@CrossOrigin
@RestController
@RequestMapping(value = RestUrl.BASE_PATH + OrupRestURL.ORUP)
public class OrupController implements LoggedController {
    @Autowired
    JpaBaseDao baseDao;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrupService orupService;

    //region 用户注册登陆
    /**
     * 用户登入
     *
     * @param loginName 登陆名
     * @param password  密码
     * @param response  响应体
     * @return 登入结果, 成功则返回 token
     */
    @ApiOperation(value = "用户登入")
    @PostMapping(value = "login")
    public String login(@RequestParam String loginName,
                        @RequestParam String password,
                        HttpServletResponse response
    ) {
        UserEntity userEntity = userRepository.findUserEntityByLoginName(loginName).orElse(new UserEntity());
        if (password.equals(userEntity.getPassword())) {
            String token = JwtUtil.generateToken(userEntity);
            return success(token, "登录成功");
        }
        return fail("登录失败, 账号或者密码错误");
    }

    /**
     * 用户注册
     *
     * @param registerUser 用户信息
     * @return 新注册用户信息
     */
    @ApiOperation(value = "用户注册")
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
     * 用户密码修改
     *
     * @return 新注册用户信息
     */
    @ApiOperation(value = "用户密码修改")
    @PostMapping(value = "passwordChange")
    public String passwordChange(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 HttpServletRequest request) {
        UserEntity user = getUser(request);
        user = userRepository.findById(user.getId()).orElseThrow(RuntimeException::new);
        if (user.getPassword().equals(oldPassword)) {
            try {
                orupService.passwordChange(user.getId(), newPassword);
                return success(null, "密码修改成功");
            } catch (Exception e) {
                return fail("密码修改失败");
            }
        } else {
            return fail("用户原密码错误");
        }
    }

    /**
     * 用户登出
     *
     * @param response 响应体
     * @return 登出结果
     */
    @ApiOperation(value = "用户登出")
    @PostMapping(value = "logout")
    public String logout(HttpServletResponse response) {
        return success();
    }

    /**
     * 获取当前用户信息
     *
     * @param request 请求头
     * @return 获取到用户信息, 未登录则返回游客用户
     */
    @ApiOperation(value = "获取当前用户信息")
    @GetMapping(value = "currentUser")
    public String currentUser(@RequestParam(defaultValue = "true") Boolean useCache, HttpServletRequest request) {
        UserEntity userEntity = Optional.ofNullable(getUser(request)).orElseGet(() -> {
            UserEntity guest = new UserEntity();
            guest.setUserName("游客");
            return guest;
        });
        if (!useCache) {
            userEntity = userRepository.findById(userEntity.getId()).orElseThrow(RuntimeException::new);
        }
        return success(userEntity);
    }
    //endregion

    //region 组织管理-用户管理
    /**
     * 用户管理: 创建用户
     *
     * @param userDTO 用户DTO
     * @param request 请求体
     * @return 新用户实体
     */
    @ApiOperation(value = "添加用户")
    @PostMapping(value = "addUser")
    public String addUser(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        UserEntity currentUser = getUser(request);
        UserEntity newUser = orupService.addUser(userDTO, currentUser);
        return success(newUser);
    }

    /**
     * 用户管理: 修改用户信息
     *
     * @param userDTO 用户DTO
     * @param request 请求体
     * @return 新用户实体
     */
    @ApiOperation(value = "修改用户信息")
    @PostMapping(value = "modifyUser")
    public String modifyUser(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        UserEntity currentUser = getUser(request);
        UserEntity newUser = orupService.modifyUser(userDTO, currentUser);
        return success(newUser);
    }

    /**
     * 用户管理: 挂起用户
     *
     * @param userId 用户id
     * @param request 请求体
     * @return 新用户实体
     */
    @ApiOperation(value = "挂起用户")
    @PostMapping(value = "suspendUser")
    public String suspendUser(@RequestBody Number userId, HttpServletRequest request) {
        UserEntity currentUser = getUser(request);
        orupService.suspendUser(userId, currentUser);
        return success();
    }

    /**
     * 用户管理: 删除用户
     *
     * @param userId  用户id
     * @param request 请求体
     * @return 新用户实体
     */
    @ApiOperation(value = "删除用户信息")
    @PostMapping(value = "delUser")
    public String delUser(@RequestParam Number userId, HttpServletRequest request) {
        UserEntity currentUser = getUser(request);
        orupService.delUser(userId, currentUser);
        return success();
    }

    /**
     * 用户管理: 获取用户信息
     *
     * @param userId  用户id
     * @param request 请求体
     * @return 用户实体信息
     */
    @GetMapping(value = "getUser")
    public String getUser(@RequestParam Number userId, HttpServletRequest request) {
        UserEntity userEntity = orupService.getUser(userId);
        return success(userEntity);
    }
    //endregion

}
