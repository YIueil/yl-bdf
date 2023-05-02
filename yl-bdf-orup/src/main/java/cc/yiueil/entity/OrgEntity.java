package cc.yiueil.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "org", schema = "yl_acc")
public class OrgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator")
    @SequenceGenerator(name = "generator", schema = "yl_acc", sequenceName = "s_org", allocationSize = 1)
    private Long id;
    private String guid;
    private String name;
    private String code;
    private String type;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Long createUserId;
    private Long parentId;
}
