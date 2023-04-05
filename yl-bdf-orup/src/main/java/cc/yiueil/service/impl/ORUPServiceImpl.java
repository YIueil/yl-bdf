package cc.yiueil.service.impl;

import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.entity.UserEntity;
import cc.yiueil.service.ORUPService;
import cc.yiueil.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ORUPServiceImpl implements ORUPService {
    @Autowired
    JpaBaseDao baseDao;

    @Override
    @Transactional
    public UserEntity registerUser(UserEntity registerUser) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(registerUser.getUserName());
        userEntity.setLoginName(registerUser.getLoginName());
        userEntity.setPassword(registerUser.getPassword());
        userEntity.setPhoneNumber(registerUser.getPhoneNumber());
        userEntity.setExtendProperty1(registerUser.getExtendProperty1());
        userEntity.setExtendProperty2(registerUser.getExtendProperty2());
        userEntity.setExtendProperty3(registerUser.getExtendProperty3());
        userEntity.setExtendProperty4(registerUser.getExtendProperty4());
        userEntity.setCreateTime(LocalDateTime.now());
        return baseDao.save(userEntity);
    }
}
