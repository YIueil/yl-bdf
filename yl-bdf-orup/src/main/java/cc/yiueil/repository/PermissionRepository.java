package cc.yiueil.repository;

import cc.yiueil.entity.PermissionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * PermissionRepository
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/8/1 23:12
 */
public interface PermissionRepository extends CrudRepository<PermissionEntity, Long> {
    /**
     * 根据用户id查询用户所有权限
     * @param userId 用户id
     * @return 用户权限集合
     */
    @Query("select distinct p from UserEntity u left join  UserRoleEntity ur on ur.userId = u.id left join RoleEntity r on r.id = ur.roleId left join RolePermissionEntity rp on rp.roleId = r.id left join PermissionEntity p on p.id = rp.permissionId where u.id = :userId")
    List<PermissionEntity> findPermissionsByUser(@Param(value = "userId") Long userId);
}
