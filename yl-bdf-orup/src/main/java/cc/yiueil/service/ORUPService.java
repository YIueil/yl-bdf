package cc.yiueil.service;

import cc.yiueil.entity.UserEntity;

public interface ORUPService {
    /**
     * 注册用户
     * @param registerUser 新注册用户
     * @return 新注册成功的用户
     */
    UserEntity registerUser(UserEntity registerUser);

    /**
     * 修改密码
     * @param userId 用户id
     * @param newPassword 新密码
     */
    void passwordChange(Long userId, String newPassword);
}
