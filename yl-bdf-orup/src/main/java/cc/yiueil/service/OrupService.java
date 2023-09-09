package cc.yiueil.service;

import cc.yiueil.dto.PermissionDto;
import cc.yiueil.dto.RoleDto;
import cc.yiueil.dto.UserDto;

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
}
