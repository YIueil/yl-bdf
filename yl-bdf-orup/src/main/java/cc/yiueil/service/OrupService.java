package cc.yiueil.service;

import cc.yiueil.dto.*;

import java.util.List;

/**
 * 核心ORUP接口
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/5/30 22:17
 */
public interface OrupService {
    /**
     * 注册用户
     *
     * @param registerUser 新注册用户
     * @return 新注册成功的用户
     */
    UserDto registerUser(UserDto registerUser);

    /**
     * 修改密码
     *
     * @param userDto     用户DTO
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void passwordChange(UserDto userDto, String oldPassword, String newPassword);

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return 用户实体信息
     */
    UserDto getUser(Number userId);

    /**
     * 添加用户
     *
     * @param userDto     用户DTO
     * @param currentUser 请求体
     * @return 用户实体信息
     */
    UserDto addUser(UserDto userDto, UserDto currentUser);

    /**
     * 修改用户信息
     *
     * @param userDto     用户实体
     * @param currentUser 当前用户
     * @return 编辑后的用户信息
     */
    UserDto modifyUser(UserDto userDto, UserDto currentUser);

    /**
     * 删除用户信息
     *
     * @param userId      用户id
     * @param currentUser 当前用户
     */
    void delUser(Number userId, UserDto currentUser);

    /**
     * 挂起用户
     *
     * @param userId      用户id
     * @param currentUser 当前用户
     */
    void suspendUser(Number userId, UserDto currentUser);

    /**
     * 根据登陆名查找用户
     *
     * @param loginName 用户登陆名
     * @return 用户数据传输类
     */
    UserDto findUserByLoginName(String loginName);

    /**
     * 获取用户所有权限
     *
     * @param user 操作用户
     * @return 权限集合
     */
    List<PermissionDto> getUserPermissions(UserDto user);

    /**
     * 获取用户所有角色
     *
     * @param user 操作用户
     * @return 角色集合
     */
    List<RoleDto> getUserRoles(UserDto user);

    /**
     * 获取所有应用集合
     *
     * @return 应用集合
     */
    List<ApplicationDto> getAllApplications();

    /**
     * 添加应用
     *
     * @param applicationDto 应用Dto
     * @param currentUser    当前用户
     * @return 新增的应用
     */
    ApplicationDto addApplication(ApplicationDto applicationDto, UserDto currentUser);

    /**
     * 给应用批量设置应用管理员
     *
     * @param applicationId 应用id
     * @param userIds       用户id集合
     * @param currentUser   当前用户
     */
    void addApplicationManager(Long applicationId, List<Long> userIds, UserDto currentUser);

    /**
     * 添加应用功能
     *
     * @param functionDto 应用功能Dto
     * @param currentUser 当前用户
     * @return 新添加的应用功能
     */
    FunctionDto addApplicationFunction(FunctionDto functionDto, UserDto currentUser);

    /**
     * 应用功能授权
     *
     * @param functionId  应用功能id
     * @param roleIds     角色id集合
     * @param currentUser 当前用户
     */
    void applicationAuthorization(Long functionId, List<Long> roleIds, UserDto currentUser);

    /**
     * 删除应用
     *
     * @param applicationId 应用id
     * @param currentUser   请求体
     */
    void delApplication(Long applicationId, UserDto currentUser);

    /**
     * 获取应用的所有应用功能
     *
     * @param applicationId 应用id
     * @return 应用下的素有应用功能集合
     */
    List<FunctionDto> getApplicationFunctionList(Long applicationId);

    /**
     * 删除应用功能
     *
     * @param functionId  应用功能id
     * @param currentUser 当前用户
     */
    void delFunction(Long functionId, UserDto currentUser);

    /**
     * 添加机构
     * @param orgDto 机构Dto
     * @param user 当前用户
     * @return 添加好的机构Dto
     */
    OrgDto addOrganization(OrgDto orgDto, UserDto user);

    /**
     * 获取机构列表
     * @return 机构列表
     */
    List<OrgDto> getOrgList();

    /**
     * 通过id获取机构
     * @param id 机构id
     * @return 机构Dto
     */
    OrgDto findOrgById(Long id);

    /**
     * 修改机构实体
     * @param orgDto 机构Dto
     * @return 修改后的机构dto
     */
    OrgDto modifyOrg(OrgDto orgDto);

    /**
     * 通过id删除机构
     * @param id 删除机构
     * @param user 当前用户
     */
    void delOrgById(Long id, UserDto user);

    /**
     * 添加机构用户
     * @param orgId 机构id
     * @param userIds 用户id集合
     * @param user 当前用户
     * @return 添加的机构用户对象
     */
    void addOrgUser(Long orgId, List<Long> userIds, UserDto user);

    /**
     * 添加角色
     * @param roleDto 角色Dto
     * @param userDto 当前用户
     * @return 新增的RoleDto
     */
    RoleDto addRole(RoleDto roleDto, UserDto userDto);


    /**
     * 获取角色
     * @param id 角色id
     * @return RoleDto
     */
    RoleDto findRoleById(Long id);

    /**
     * 获取角色机构
     * @return RoleList
     */
    List<RoleDto> getRoleList();

    /**
     * 修改角色
     * @param roleDto roleDto
     * @param user 当前用户
     * @return 修改后的RoleDto
     */
    RoleDto modifyRole(RoleDto roleDto, UserDto user);

    /**
     * 删除角色
     * @param roleId 角色id
     * @param user 当前用户
     */
    void delRole(Long roleId, UserDto user);

    /**
     * 向角色中添加用户
     *
     * @param roleId  角色id
     * @param userIds 用户id集合
     * @param user    当前用户
     */
    void addRoleUser(Long roleId, List<Long> userIds, UserDto user);

    /**
     * 修改应用功能
     *
     * @param functionDto 功能dto
     * @param currentUser 当前用户
     * @return 修改后的 FunctionDto
     */
    FunctionDto modifyFunction(FunctionDto functionDto, UserDto currentUser);
}
