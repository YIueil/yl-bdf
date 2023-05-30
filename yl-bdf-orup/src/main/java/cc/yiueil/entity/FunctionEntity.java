package cc.yiueil.entity;

import cc.yiueil.lang.instance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 功能实体
 * @author 弋孓 YIueil@163.com
 * @date 2023/5/30 22:10
 * @version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "function", schema = "yl_acc")
public class FunctionEntity implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator")
    @SequenceGenerator(name = "generator", schema = "yl_acc", sequenceName = "s_function", allocationSize = 1)
    private Long id;
    private String guid;
    private String name;
    private String description;
    private String url;
    private String type;
    private String method;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Long createUserId;
    private Long parentId;
    private Long applicationId;
}