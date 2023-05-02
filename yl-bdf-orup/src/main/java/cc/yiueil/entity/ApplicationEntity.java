package cc.yiueil.entity;

import cc.yiueil.lang.instance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "application", schema = "yl_acc")
public class ApplicationEntity implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator")
    @SequenceGenerator(name = "generator", schema = "yl_acc", sequenceName = "s_application", allocationSize = 1)
    private Long id;
    private String guid;
    private String name;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Long createUserId;
}