package cc.yiueil.service;

import cc.yiueil.entity.UserEntity;

public interface ORUPService {
    /**
     * 注册用户
     * @param registerUser 新注册用户
     * @return 新注册成功的用户
     */
    UserEntity registerUser(UserEntity registerUser);
}