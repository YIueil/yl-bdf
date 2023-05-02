package cc.yiueil.entity;

import cc.yiueil.lang.instance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_role", schema = "yl_acc")
public class UserRoleEntity implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator")
    @SequenceGenerator(name = "generator", schema = "yl_acc", sequenceName = "s_user_role", allocationSize = 1)
    private Long id;
    private String guid;
    private Long userId;
    private Long roleId;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Long createUserId;
}