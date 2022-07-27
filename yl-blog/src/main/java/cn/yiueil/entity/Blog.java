package cn.yiueil.entity;

import cn.yiueil.lang.instance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_blog")
public class Blog implements BaseEntity<Integer>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g_blog")
    @SequenceGenerator(name = "g_blog", sequenceName = "s_blog", allocationSize = 1)
    private Integer id;

    private String guid;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer createUser;

    @Column(length = 100, columnDefinition = "标题")
    private String title;

    @Column(length = 1024, columnDefinition = "内容")
    private String content;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "tr_blog_tag",
            joinColumns = {@JoinColumn(name = "fk_blog_id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_tag_id")}
    )
    private List<Tag> tags;
}
