package cc.yiueil.entity;

import cc.yiueil.lang.instance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_org", schema = "yl_acc")
public class UserOrgEntity implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator")
    @SequenceGenerator(name = "generator", schema = "yl_acc", sequenceName = "s_user_org", allocationSize = 1)
    private Long id;
    private String guid;
    private Long userId;
    private Long orgId;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Long createUserId;
}