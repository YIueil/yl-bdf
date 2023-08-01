package cc.yiueil.controller;

import cc.yiueil.entity.UserEntity;
import cc.yiueil.general.RestUrl;
import cc.yiueil.serivce.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * UserController 用户控制器
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/8/1 22:56
 */
@RestController
@RequestMapping(value = RestUrl.BASE_PATH + "/user")
public class UserController implements LoggedController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "获取用户集合")
    @GetMapping(value = "list")
    public String getUserList() {
        return success(userService.getAllUser());
    }

    @ApiOperation(value = "获取用户所有权限集合")
    @GetMapping(value = "permissions")
    public String getUserPermission(HttpServletRequest request) {
        UserEntity user = getUser(request);
        return success(userService.getUserPermissions(user));
    }
}
