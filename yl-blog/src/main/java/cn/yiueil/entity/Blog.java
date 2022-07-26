package cn.yiueil.entity;

import cn.yiueil.lang.instance.HasGuid;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "t_blog")
public class Blog implements HasGuid {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g_blog")
    @SequenceGenerator(name = "g_blog", sequenceName = "s_blog", allocationSize = 1)
    private Integer id;

    @Column(length = 32)
    private String guid;

    @Column(length = 100, columnDefinition = "标题")
    private String title;

    @Column(length = 1024, columnDefinition = "内容")
    private String content;

    @Column(columnDefinition = "创建时间")
    private LocalDateTime createTime;

    @Column(columnDefinition = "删除时间")
    private LocalDateTime modifyTime;
}
