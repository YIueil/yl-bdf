package cn.yiueil.entity;

import cn.yiueil.lang.instance.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Author:YIueil
 * Date:2022/8/18 15:17
 * Description: 空间
 */
@Getter
@Setter
public class SpaceEntity implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g_space")
    @SequenceGenerator(name = "g_space", sequenceName = "s_space", allocationSize = 1)
    private Integer id;

    private String guid;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer createUser;

    @Column(length = 30, columnDefinition = "空间名")
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_space_id")
    List<PageEntity> pageList;
}
