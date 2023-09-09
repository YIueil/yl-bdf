package cc.yiueil.controller;

import cc.yiueil.constant.OrupRestURL;
import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.dto.UserDto;
import cc.yiueil.general.RestUrl;
import cc.yiueil.service.OrupService;
import cc.yiueil.util.BeanUtils;
import cc.yiueil.util.JwtUtil;
import cc.yiueil.vo.UserVo;
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
    OrupService orupService;

    //region 用户注册登陆
    /**
     * 用户登入
     *
     * @param loginName 登陆名
     * @param password  密码
     * @return 登入结果, 成功则返回 token
     */
    @ApiOperation(value = "用户登入")
    @PostMapping(value = "login")
    public String login(@RequestParam String loginName,
                        @RequestParam String password
    ) {
        UserDto userDto = orupService.findUserByLoginName(loginName);
        if (password.equals(userDto.getPassword())) {
            String token = JwtUtil.generateToken(userDto);
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
    public String register(@RequestBody UserDto registerUser) {
        try {
            return success(orupService.registerUser(registerUser));
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
        UserDto currentUser = getUser(request);
        if (currentUser.getPassword().equals(oldPassword)) {
            try {
                orupService.passwordChange(currentUser, oldPassword, newPassword);
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
    public String currentUser(@RequestParam(defaultValue = "true") Boolean useCache,
                              @RequestParam(defaultValue = "false") Boolean loadPermissions,
                              @RequestParam(defaultValue = "false") Boolean loadRoles,
                              HttpServletRequest request) {
        UserVo userVo = new UserVo();
        UserDto userDto = Optional.ofNullable(getUser(request)).orElseGet(() -> {
            UserDto guest = new UserDto();
            guest.setUserName("游客");
            return guest;
        });
        if (!useCache) {
            userDto = orupService.getUser(userDto.getId());
        }
        if (loadPermissions) {
            userVo.setPermissionDtoList(orupService.getUserPermissions(userDto));
        }
        if (loadRoles) {
            userVo.setRoleDtoList(orupService.getUserRoles(userDto));
        }
        return success(BeanUtils.copyProperties(userDto, userVo));
    }
    //endregion

    //region 用户管理

    /**
     * 用户管理: 创建用户
     *
     * @param userDto 用户DTO
     * @param request 请求体
     * @return 新用户实体
     */
    @ApiOperation(value = "添加用户")
    @PostMapping(value = "addUser")
    public String addUser(@RequestBody UserDto userDto, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        UserDto newUser = orupService.addUser(userDto, currentUser);
        return success(newUser);
    }

    /**
     * 用户管理: 修改用户信息
     *
     * @param userDto 用户DTO
     * @param request 请求体
     * @return 新用户实体
     */
    @ApiOperation(value = "修改用户信息")
    @PostMapping(value = "modifyUser")
    public String modifyUser(@RequestBody UserDto userDto, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        UserDto newUser = orupService.modifyUser(userDto, currentUser);
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
        UserDto currentUser = getUser(request);
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
        UserDto currentUser = getUser(request);
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
    @ApiOperation(value = "获取用户")
    @GetMapping(value = "getUser")
    public String getUser(@RequestParam Number userId, HttpServletRequest request) {
        UserDto currentUser = orupService.getUser(userId);
        return success(currentUser);
    }
    //endregion

    //region 角色权限

    /**
     * 角色权限: 获取用户所有权限集合
     *
     * @param request 请求体
     * @return 权限集合
     */
    @ApiOperation(value = "获取用户所有权限集合")
    @GetMapping(value = "permissions")
    public String getUserPermissions(HttpServletRequest request) {
        UserDto userDto = getUser(request);
        return success(orupService.getUserPermissions(userDto));
    }

    /**
     * 角色权限: 获取用户所有角色集合
     *
     * @param request 请求体
     * @return 角色集合
     */
    @ApiOperation(value = "获取用户所有角色集合")
    @GetMapping(value = "roles")
    public String getUserRoles(HttpServletRequest request) {
        UserDto userDto = getUser(request);
        return success(orupService.getUserRoles(userDto));
    }
    //endregion
}
