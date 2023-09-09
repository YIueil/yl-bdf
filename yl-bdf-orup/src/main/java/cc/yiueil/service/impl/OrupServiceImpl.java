package cc.yiueil.service.impl;

import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.dto.PermissionDto;
import cc.yiueil.dto.RoleDto;
import cc.yiueil.dto.UserDto;
import cc.yiueil.entity.PermissionEntity;
import cc.yiueil.entity.RoleEntity;
import cc.yiueil.entity.UserEntity;
import cc.yiueil.enums.UserStateEnum;
import cc.yiueil.exception.BusinessException;
import cc.yiueil.repository.PermissionRepository;
import cc.yiueil.repository.RoleRepository;
import cc.yiueil.repository.UserRepository;
import cc.yiueil.service.OrupService;
import cc.yiueil.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 核心ORUP服务
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/5/30 22:14
 */
@Service
public class OrupServiceImpl implements OrupService {
    @Autowired
    JpaBaseDao baseDao;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public UserDto registerUser(UserDto registerUser) {
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
        return BeanUtils.copyProperties(baseDao.save(userEntity), new UserDto());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void passwordChange(UserDto currentUser, String oldPassword, String newPassword) {
        UserEntity userEntity = userRepository.findById(currentUser.getId()).orElseThrow(() -> new BusinessException("用户未找到"));
        baseDao.findById(UserEntity.class, userEntity.getId()).ifPresent(user -> {
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                baseDao.save(user);
            } else {
                throw new BusinessException("旧密码和原密码不符");
            }
        });
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Number userId) {
        return BeanUtils.copyProperties(
                baseDao.findById(UserEntity.class, userId).orElseThrow(() -> new BusinessException("用户未找到")),
                new UserDto());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public UserDto addUser(UserDto userDto, UserDto currentUser) {
        UserEntity userEntity = BeanUtils.copyProperties(userDto, new UserEntity());
        userEntity.setCreateUserId(currentUser.getId());
        baseDao.save(userEntity);
        return BeanUtils.copyProperties(userEntity, new UserDto());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public UserDto modifyUser(UserDto userDto, UserDto currentUser) {
        UserEntity userEntity = BeanUtils.copyProperties(userDto, new UserEntity());
        baseDao.save(userEntity);
        return BeanUtils.copyProperties(userEntity, new UserDto());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delUser(Number userId, UserDto currentUser) {
        baseDao.deleteById(UserEntity.class, userId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void suspendUser(Number userId, UserDto currentUser) {
        baseDao.findById(UserEntity.class, userId).ifPresent(userEntity -> {
            userEntity.setState(UserStateEnum.normal.getState());
            baseDao.save(userEntity);
        });
    }

    @Override
    public UserDto findUserByLoginName(String loginName) {
        return BeanUtils.copyProperties(
                userRepository.findUserEntityByLoginName(loginName).orElseThrow(() -> new BusinessException("用户未找到")),
                new UserDto()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionDto> getUserPermissions(UserDto user) {
        List<PermissionEntity> permissionEntityList = permissionRepository.findPermissionsByUserId(user.getId());
        return permissionEntityList.stream()
                .map(permissionEntity -> BeanUtils.copyProperties(permissionEntity, new PermissionDto()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> getUserRoles(UserDto user) {
        List<RoleEntity> roleEntityList = roleRepository.findRolesByUserId(user.getId());
        return roleEntityList.stream()
                .map(roleEntity -> BeanUtils.copyProperties(roleEntity, new RoleDto()))
                .collect(Collectors.toList());
    }
}
