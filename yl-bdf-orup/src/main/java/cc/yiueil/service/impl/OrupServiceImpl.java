package cc.yiueil.service.impl;

import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.dto.*;
import cc.yiueil.entity.*;
import cc.yiueil.enums.UserStateEnum;
import cc.yiueil.exception.BusinessException;
import cc.yiueil.repository.*;
import cc.yiueil.service.OrupService;
import cc.yiueil.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    FunctionRepository functionRepository;

    @Autowired
    OrgRepository orgRepository;

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
        UserEntity userEntity = userRepository.findById(userDto.getId()).orElseThrow(() -> new BusinessException("用户未找到"));
        UserEntity modifyUser = BeanUtils.copyProperties(userDto, userEntity, true);
        baseDao.save(modifyUser);
        return BeanUtils.copyProperties(modifyUser, new UserDto());
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
                userRepository.findUserEntityByLoginName(loginName).orElseThrow(() -> new BusinessException("登录失败, 账号或者密码错误")),
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

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationDto> getAllApplications() {
        Iterable<ApplicationEntity> applicationEntities = applicationRepository.findAll();
        return StreamSupport.stream(applicationEntities.spliterator(), false)
                .map(applicationEntity -> BeanUtils.copyProperties(applicationEntity, new ApplicationDto()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ApplicationDto addApplication(ApplicationDto applicationDto, UserDto currentUser) {
        ApplicationEntity newApplicationEntity = baseDao.save(BeanUtils.copyProperties(applicationDto, new ApplicationEntity()));
        return BeanUtils.copyProperties(newApplicationEntity, new ApplicationDto());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addApplicationManager(Long applicationId, List<Long> userIds, UserDto currentUser) {
        for (Long userId : userIds) {
            ApplicationManagerEntity applicationManagerEntity = new ApplicationManagerEntity();
            applicationManagerEntity.setApplicationId(applicationId);
            applicationManagerEntity.setManagerId(userId);
            applicationManagerEntity.setCreateUserId(currentUser.getId());
            baseDao.save(applicationManagerEntity);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public FunctionDto addApplicationFunction(FunctionDto functionDto, UserDto currentUser) {
        ApplicationEntity applicationEntity = BeanUtils.copyProperties(functionDto, new ApplicationEntity());
        ApplicationEntity newApplicationFunction = baseDao.save(applicationEntity);
        return BeanUtils.copyProperties(newApplicationFunction, new FunctionDto());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delFunction(Long functionId, UserDto currentUser) {
        baseDao.deleteById(FunctionEntity.class, functionId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void applicationAuthorization(Long functionId, List<Long> roleIds, UserDto currentUser) {
        List<RoleFunctionEntity> roleFunctionEntityList = roleIds.stream()
                .map(roleId -> {
                    RoleFunctionEntity roleFunctionEntity = new RoleFunctionEntity();
                    roleFunctionEntity.setFunctionId(functionId);
                    roleFunctionEntity.setRoleId(roleId);
                    roleFunctionEntity.setCreateUserId(currentUser.getId());
                    return roleFunctionEntity;
                })
                .collect(Collectors.toList());
        baseDao.batchSave(roleFunctionEntityList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delApplication(Long applicationId, UserDto currentUser) {
        baseDao.deleteById(ApplicationEntity.class, applicationId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FunctionDto> getApplicationFunctionList(Long applicationId) {
        List<FunctionEntity> functionEntities = functionRepository.findFunctionEntitiesByApplicationId(applicationId);
        return functionEntities.stream()
                .map(functionEntity -> BeanUtils.copyProperties(functionEntity, new FunctionDto()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public OrgDto addOrganization(OrgDto orgDto, UserDto user) {
        OrgEntity orgEntity = BeanUtils.copyProperties(orgDto, new OrgEntity());
        return BeanUtils.copyProperties(baseDao.save(orgEntity), new OrgDto());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgDto> getOrgList() {
        Iterable<OrgEntity> orgEntityList = orgRepository.findAll();
        return StreamSupport.stream(orgEntityList.spliterator(), false)
                .map(orgEntity -> BeanUtils.copyProperties(orgEntity, new OrgDto()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrgDto findOrgById(Long id) {
        return BeanUtils.copyProperties(orgRepository.findById(id), new OrgDto());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public OrgDto modifyOrg(OrgDto orgDto) {
        if (orgDto.getId() == null) {
            throw new BusinessException("修改接口传入的实体需要具有id");
        }
        OrgEntity orgEntity = orgRepository.findById(orgDto.getId()).orElseThrow(() -> new BusinessException("没有查询到该机构"));
        OrgEntity modifyOrgEntity = BeanUtils.copyProperties(orgDto, orgEntity);
        return BeanUtils.copyProperties(baseDao.save(modifyOrgEntity), new OrgDto());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delOrgById(Long id, UserDto user) {
        orgRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addOrgUser(Long orgId, List<Long> userIds, UserDto user) {
        List<UserOrgEntity> userOrgEntities = userIds.stream()
                .map(userId -> {
                    UserOrgEntity userOrgEntity = new UserOrgEntity();
                    userOrgEntity.setUserId(userId);
                    userOrgEntity.setCreateUserId(user.getId());
                    userOrgEntity.setOrgId(orgId);
                    return userOrgEntity;
                }).collect(Collectors.toList());
        baseDao.batchSave(userOrgEntities);
    }
}
