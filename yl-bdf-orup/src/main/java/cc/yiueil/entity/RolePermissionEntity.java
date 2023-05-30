package cc.yiueil.entity;

import cc.yiueil.lang.instance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 角色权限关联实体
 * @author 弋孓 YIueil@163.com
 * @date 2023/5/30 22:13
 * @version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "role_permission", schema = "yl_acc")
public class RolePermissionEntity implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator")
    @SequenceGenerator(name = "generator", schema = "yl_acc", sequenceName = "s_role_permission", allocationSize = 1)
    private Long id;
    private String guid;
    private Long roleId;
    private Long permissionId;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Long createUserId;
}