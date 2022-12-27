package cn.yiueil.entity;

import cn.yiueil.lang.instance.BaseEntity;
import cn.yiueil.lang.instance.HasParent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_page")
@NoArgsConstructor
@AllArgsConstructor
public class PageEntity implements BaseEntity<Integer>, HasParent<Integer>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g_page")
    @SequenceGenerator(name = "g_page", sequenceName = "s_page", allocationSize = 1)
    private Integer id;

    private String guid;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer createUser;

    private Integer parentId;

    @Column(length = 500)
    private String iconUrl;

    @Column(length = 100)
    private String title;

    @Column(length = 1024)
    private String content;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tr_page_tag",
            joinColumns = {@JoinColumn(name = "fk_page_id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_tag_id")}
    )
    private List<TagEntity> tags;
}
