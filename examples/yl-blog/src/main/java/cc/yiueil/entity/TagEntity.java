package cc.yiueil.entity;

import cc.yiueil.lang.instance.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "t_tag")
@NoArgsConstructor
public class TagEntity implements BaseEntity<Integer>, Serializable {
    public TagEntity(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g_tag")
    @SequenceGenerator(name = "g_tag", sequenceName = "s_tag", allocationSize = 1)
    private Integer id;

    private String guid;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer createUserId;

    @Column(length = 30)
    private String name;
}
