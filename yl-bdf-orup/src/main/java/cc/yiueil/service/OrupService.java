package cc.yiueil.service;

import cc.yiueil.dto.UserDTO;
import cc.yiueil.entity.UserEntity;

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
    UserEntity registerUser(UserEntity registerUser);

    /**
     * 修改密码
     *
     * @param userId      用户id
     * @param newPassword 新密码
     */
    void passwordChange(Long userId, String newPassword);

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return 用户实体信息
     */
    UserEntity getUser(Number userId);

    /**
     * 添加用户
     *
     * @param userDTO     用户DTO
     * @param currentUser 请求体
     * @return 用户实体信息
     */
    UserEntity addUser(UserDTO userDTO, UserEntity currentUser);

    /**
     * 修改用户信息
     *
     * @param userDTO     用户实体
     * @param currentUser 当前用户
     * @return 编辑后的用户信息
     */
    UserEntity modifyUser(UserDTO userDTO, UserEntity currentUser);

    /**
     * 删除用户信息
     *
     * @param userId      用户id
     * @param currentUser 当前用户
     */
    void delUser(Number userId, UserEntity currentUser);

    /**
     * 挂起用户
     *
     * @param userId      用户id
     * @param currentUser 当前用户
     */
    void suspendUser(Number userId, UserEntity currentUser);
}
