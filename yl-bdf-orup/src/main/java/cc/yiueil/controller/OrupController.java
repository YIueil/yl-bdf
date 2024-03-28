package cc.yiueil.controller;

import cc.yiueil.annotation.VerifyToken;
import cc.yiueil.constant.OrupRestUrl;
import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.dto.*;
import cc.yiueil.exception.BusinessException;
import cc.yiueil.general.RestUrl;
import cc.yiueil.lang.tree.Tree;
import cc.yiueil.lang.tree.TreeNode;
import cc.yiueil.service.OrupService;
import cc.yiueil.util.*;
import cc.yiueil.vo.PasswordStrengthVo;
import cc.yiueil.vo.UserVo;
import com.auth0.jwt.interfaces.Claim;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户账户控制器
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/5/30 22:16
 */
@Api(value = "ORUP-用户角色账户权限控制")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = RestUrl.BASE_PATH + OrupRestUrl.ORUP)
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
        try {
            UserDto userDto = orupService.findUserByLoginName(loginName);
            if (password.equals(userDto.getPassword())) {
                String token = JwtUtils.generateToken(userDto, ParseUtils.getInteger(GlobalProperties.getProperties("jwt.expire.seconds", "1800"), 1800));
                return success(userDto, "登录成功", token);
            }
        } catch (BusinessException e) {
            return fail(e.getMessage());
        }
        return fail("登录失败, 账号或者密码错误");
    }

    /**
     * 刷新token
     *
     * @param request 请求体
     * @return 带有新token的ResultVo
     */
    @ApiOperation(value = "刷新token")
    @GetMapping(value = "refreshToken")
    public String refreshToken(HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        UserDto user = orupService.getUser(currentUser.getId());
        String newToken = JwtUtils.generateToken(user, ParseUtils.getInteger(GlobalProperties.getProperties("jwt.expire.seconds", "1800"), 1800));
        return success(user, "刷新成功", newToken);
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
     * @param newPassword 新密码
     * @param oldPassword 旧密码
     * @param request     请求体
     * @return 修改结果
     */
    @ApiOperation(value = "用户密码修改")
    @PostMapping(value = "passwordChange")
    public String passwordChange(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        try {
            orupService.passwordChange(currentUser, oldPassword, newPassword);
            return success(null, "密码修改成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 用户邮件变更
     *
     * @param newMailAddress 新邮件地址
     * @param request        请求体
     * @return 变更结果
     */
    @VerifyToken
    @ApiOperation(value = "用户邮件变更")
    @RequestMapping(value = "mailChange", method = {RequestMethod.POST, RequestMethod.GET})
    public String mailChange(@RequestParam(required = false) String newMailAddress, HttpServletRequest request) {
        Map<String, Claim> contextMap = getContextMap(request);
        try {
            String mailAddress = ObjectUtils.defaultIfNull(newMailAddress, contextMap.get("newMailAddress").asString());
            Long userId = contextMap.get("userId").asLong();
            if (StringUtils.isBlank(mailAddress)) {
                return fail(null, "没有传入目标邮箱");
            }
            orupService.userMailChange(userId, mailAddress);
            return success();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return fail(null, e.getMessage());
        }
    }

    /**
     * 获取账号安全等级
     *
     * @param request 请求体
     * @return 请求体
     */
    @ApiOperation(value = "获取账号安全等级")
    @GetMapping(value = "getAccountSecurityLevel")
    public String getAccountSecurityLevel(HttpServletRequest request) {
        UserDto user = getUser(request);
        PasswordStrengthVo passwordStrengthVo = orupService.getAccountSecurityLevel(user);
        return success(passwordStrengthVo);
    }

    /**
     * 用户手机号修改
     */
    @ApiOperation(value = "用户手机号修改")
    @PostMapping(value = "phoneNumberChange")
    public String phoneNumberChange(@RequestParam String newPhoneNumber, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        try {
            orupService.phoneNumberChange(currentUser, newPhoneNumber);
            return success(null, "手机号修改成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return fail(e.getMessage());
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
     * @param userId  用户id
     * @param request 请求体
     * @return 新用户实体
     */
    @ApiOperation(value = "挂起用户")
    @PostMapping(value = "suspendUser")
    public String suspendUser(@RequestParam Long userId, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        orupService.suspendUser(userId, currentUser);
        return success();
    }

    /**
     * 批量挂起用户
     *
     * @param userIds 用户id集合
     * @param request 请求体
     * @return 请求结果
     */
    @ApiOperation(value = "批量挂起用户")
    @PostMapping(value = "suspendUserByIds")
    public String suspendUserByIds(@RequestBody List<Long> userIds, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        orupService.suspendUserByIds(userIds, currentUser);
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
    public String delUser(@RequestParam Long userId, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        orupService.delUser(userId, currentUser);
        return success();
    }

    /**
     * 通过id集合批量删除用户
     *
     * @param userIds 用户id集合
     * @param request 请求体
     * @return 接口调用结果
     */
    @ApiOperation(value = "批量删除用户信息")
    @PostMapping(value = "delUserByIds")
    public String delUserByIds(@RequestBody List<Long> userIds, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        orupService.delUserByIds(userIds, currentUser);
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
    public String getUser(@RequestParam Long userId, HttpServletRequest request) {
        UserDto currentUser = orupService.getUser(userId);
        return success(currentUser);
    }

    /**
     * 获取用户链接列表
     *
     * @param request 请求体
     * @return 用户链接集合
     */
    @ApiOperation(value = "获取用户链接列表")
    @GetMapping(value = "getUserLinks")
    public String getUserLinks(HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        List<LinkDto> linkDtoList = orupService.getUserLinks(currentUser);
        return success(linkDtoList);
    }

    /**
     * 添加链接
     *
     * @param linkDto 链接实体
     * @param request 请求体
     * @return 新增链接实体
     */
    @ApiOperation(value = "添加用户链接")
    @PostMapping(value = "addLink")
    public String addLink(@RequestBody LinkDto linkDto, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        linkDto.setUserId(currentUser.getId());
        return success(orupService.addLink(linkDto));
    }

    /**
     * 修改链接
     *
     * @param linkDto 链接实体
     * @param request 请求体
     * @return 修改后的链接
     */
    @ApiOperation(value = "修改用户链接")
    @PostMapping(value = "modifyLink")
    public String modifyLink(@RequestBody LinkDto linkDto, HttpServletRequest request) {
        return success(orupService.modifyLink(linkDto));
    }

    /**
     * 删除链接
     *
     * @param linkId  链接id
     * @param request 请求体
     * @return 接口请求结果
     */
    @ApiOperation(value = "删除用户链接")
    @PostMapping(value = "delLink")
    public String delLink(@RequestParam("id") Long linkId, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        orupService.delLink(linkId, currentUser);
        return success();
    }

    //endregion

    //region 角色权限

    @ApiOperation(value = "添加角色")
    @PostMapping(value = "addRole")
    public String addRole(@RequestBody RoleDto roleDto, HttpServletRequest request) {
        UserDto user = getUser(request);
        return success(orupService.addRole(roleDto, user));
    }

    @ApiOperation(value = "查询角色")
    @GetMapping(value = "getRole")
    public String getRole(@RequestParam Long id) {
        return success(orupService.findRoleById(id));
    }

    @ApiOperation(value = "获取角色树")
    @GetMapping(value = "getRoleTree")
    public String getRoleTree(@RequestParam(required = false) Long functionId, @RequestParam(required = false) Long permissionId) {
        List<RoleDto> roleDtoList = orupService.getRoleList(functionId, permissionId);
        return success(
                TreeUtils.build(roleDtoList.stream().map(roleDto -> {
                    Map<String, Object> extra = MapUtils.entityToMap(roleDto);
                    return new TreeNode<>(roleDto.getId(), roleDto.getParentId(), roleDto.getName(), 1, extra);
                }).collect(Collectors.toList()), 0L)
        );
    }

    @ApiOperation(value = "更新角色")
    @PostMapping(value = "updateRole")
    public String updateRole(@RequestBody RoleDto roleDto, HttpServletRequest request) {
        UserDto user = getUser(request);
        return success(orupService.modifyRole(roleDto, user));
    }

    @ApiOperation(value = "删除角色")
    @PostMapping(value = "deleteRole")
    public String deleteRole(@RequestParam Long roleId, HttpServletRequest request) {
        UserDto user = getUser(request);
        orupService.delRole(roleId, user);
        return success();
    }

    @ApiOperation(value = "添加角色用户")
    @PostMapping(value = "addRoleUser")
    public String addRoleUser(@RequestParam Long roleId, @RequestBody List<Long> userIds, HttpServletRequest request) {
        UserDto user = getUser(request);
        orupService.addRoleUser(roleId, userIds, user);
        return success();
    }

    @ApiOperation(value = "移除角色用户")
    @PostMapping(value = "delRoleUser")
    public String delRoleUser(@RequestParam Long roleId, @RequestBody List<Long> userIds, HttpServletRequest request) {
        UserDto user = getUser(request);
        orupService.delRoleUser(roleId, userIds, user);
        return success();
    }

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

    //region 应用管理

    /**
     * 获取应用集合
     *
     * @return 应用集合
     */
    @ApiOperation(value = "获取所有应用")
    @GetMapping(value = "getAllApplicationList")
    public String getAllApplications() {
        return success(orupService.getAllApplications());
    }

    /**
     * 添加或编辑应用
     *
     * @param applicationDto 应用Dto
     * @param request        请求体
     * @return 新添加的应用
     */
    @ApiOperation(value = "添加应用")
    @PostMapping(value = "addApplication")
    public String addApplication(@RequestBody ApplicationDto applicationDto, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        return success(orupService.addApplication(applicationDto, currentUser));
    }

    @ApiOperation(value = "删除应用")
    @PostMapping(value = "delApplication")
    public String delApplication(@RequestParam Long applicationId, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        orupService.delApplication(applicationId, currentUser);
        return success();
    }

    /**
     * 添加应用管理员
     *
     * @param applicationId 应用id
     * @param userIds       用户id集合
     * @param request       请求体
     * @return ResultVo
     */
    @ApiOperation(value = "添加应用管理员")
    @PostMapping(value = "addApplicationManager")
    public String addApplicationManager(@RequestParam Long applicationId, @RequestBody List<Long> userIds, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        orupService.addApplicationManager(applicationId, userIds, currentUser);
        return success();
    }

    /**
     * 获取应用功能列表树
     *
     * @param applicationId 应用id
     * @param request       请求体
     * @return ResultVo
     */
    @ApiOperation(value = "获取应用功能树")
    @GetMapping(value = "getApplicationFunctionTree")
    public String getApplicationFunctionTree(@RequestParam Long applicationId, HttpServletRequest request) {
        List<FunctionDto> functionDtoList = orupService.getApplicationFunctionList(applicationId);
        return success(buildFunctionTree(functionDtoList));
    }

    /**
     * 获取用戶应用功能列表树
     *
     * @param applicationId 应用id
     * @param request       请求体
     * @return ResultVo
     */
    @ApiOperation(value = "获取应用功能树")
    @GetMapping(value = "getUserFunctions")
    public String getUserFunctions(@RequestParam Long applicationId, HttpServletRequest request) {
        UserDto user = getUser(request);
        List<FunctionDto> functionDtoList = orupService.getUserFunctions(applicationId, user);
        return success(buildFunctionTree(functionDtoList));
    }

    private List<Tree<Long>> buildFunctionTree(List<FunctionDto> functionDtoList) {
        return TreeUtils.build(functionDtoList.stream().map(functionDto -> {
            Map<String, Object> extra = new HashMap<>(functionDtoList.size());
            extra.put("id", functionDto.getId());
            extra.put("guid", functionDto.getGuid());
            extra.put("parentId", functionDto.getParentId());
            extra.put("applicationId", functionDto.getApplicationId());
            extra.put("description", functionDto.getDescription());
            extra.put("method", functionDto.getMethod());
            extra.put("rightName", functionDto.getRightName());
            extra.put("name", functionDto.getName());
            extra.put("type", functionDto.getType());
            extra.put("url", functionDto.getUrl());
            return new TreeNode<>(functionDto.getId(), functionDto.getParentId(), functionDto.getName(), 1, extra);
        }).collect(Collectors.toList()), 0L);
    }


    /**
     * 添加应用功能
     *
     * @param functionDto 应用功能数据传输类
     * @param request     请求体
     * @return ResultVo
     */
    @ApiOperation(value = "添加应用功能")
    @PostMapping(value = "addApplicationFunction")
    public String addApplicationFunction(@RequestBody FunctionDto functionDto, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        return success(orupService.addApplicationFunction(functionDto, currentUser));
    }

    @ApiOperation(value = "修改应用功能")
    @PostMapping(value = "modifyApplicationFunction")
    public String modifyApplicationFunction(@RequestBody FunctionDto functionDto, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        return success(orupService.modifyFunction(functionDto, currentUser));
    }

    /**
     * 删除应用功能
     *
     * @param functionId 应用功能id
     * @param request    请求体
     * @return ResultVo
     */
    @ApiOperation(value = "删除应用功能")
    @PostMapping(value = "delApplicationFunction")
    public String delApplicationFunction(@RequestParam Long functionId, HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        orupService.delFunction(functionId, currentUser);
        return success();
    }

    /**
     * 应用功能授权
     *
     * @param request 请求体
     * @return ResultVo
     */
    @ApiOperation(value = "应用功能授权")
    @PostMapping(value = "applicationAuthorization")
    public String applicationAuthorization(@RequestParam Long functionId,
                                           @RequestBody List<Long> roleIds,
                                           HttpServletRequest request) {
        UserDto currentUser = getUser(request);
        orupService.applicationAuthorization(functionId, roleIds, currentUser);
        return success();
    }


    //endregion

    //region 机构管理

    @ApiOperation(value = "创建机构")
    @PostMapping(value = "addOrg")
    public String addOrganization(@RequestBody OrgDto orgDto, HttpServletRequest request) {
        UserDto user = getUser(request);
        return success(orupService.addOrganization(orgDto, user));
    }

    @ApiOperation(value = "查询机构列表")
    @GetMapping(value = "getOrgTree")
    public String getOrgTree() {
        List<OrgDto> orgDtoList = orupService.getOrgList();
        return success(
                TreeUtils.build(orgDtoList.stream().map(orgDto -> {
                    Map<String, Object> extra = new HashMap<>(orgDtoList.size());
                    extra.put("id", orgDto.getId());
                    extra.put("guid", orgDto.getGuid());
                    extra.put("parentId", orgDto.getParentId());
                    extra.put("description", orgDto.getDescription());
                    extra.put("name", orgDto.getName());
                    extra.put("code", orgDto.getCode());
                    extra.put("type", orgDto.getType());
                    return new TreeNode<>(orgDto.getId(), orgDto.getParentId(), orgDto.getName(), 1, extra);
                }).collect(Collectors.toList()), 0L)
        );
    }

    @ApiOperation(value = "根据ID查询机构信息")
    @GetMapping(value = "getOrg")
    public String getOrgById(@RequestParam Long id) {
        OrgDto orgDto = orupService.findOrgById(id);
        return success(orgDto);
    }

    @ApiOperation(value = "更新机构信息")
    @PostMapping(value = "updateOrg")
    public String updateOrg(@RequestBody OrgDto orgDto,
                            HttpServletRequest request) {
        return success(orupService.modifyOrg(orgDto));
    }

    @ApiOperation(value = "删除机构")
    @PostMapping(value = "deleteOrg")
    public String deleteOrg(@RequestParam Long id, HttpServletRequest request) {
        UserDto user = getUser(request);
        orupService.delOrgById(id, user);
        return success();
    }

    @ApiOperation(value = "向机构添加用户")
    @PostMapping(value = "addOrgUser")
    public String addOrgUser(@RequestParam Long orgId, @RequestBody List<Long> userIds, HttpServletRequest request) {
        UserDto user = getUser(request);
        orupService.addOrgUser(orgId, userIds, user);
        return success();
    }

    @ApiOperation(value = "移除机构用户")
    @PostMapping(value = "delOrgUser")
    public String delOrgUser(@RequestParam Long orgId, @RequestBody List<Long> userIds, HttpServletRequest request) {
        UserDto user = getUser(request);
        orupService.delOrgUser(orgId, userIds, user);
        return success();
    }
    //endregion
}
