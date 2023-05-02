package cc.yiueil.entity;

import cc.yiueil.lang.instance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "role", schema = "yl_acc")
public class RoleEntity implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator")
    @SequenceGenerator(name = "generator", schema = "yl_acc", sequenceName = "s_role", allocationSize = 1)
    private Long id;
    private String guid;
    private String name;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Long createUserId;
    private Long parentId;
}