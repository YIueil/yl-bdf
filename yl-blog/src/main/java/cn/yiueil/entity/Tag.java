package cn.yiueil.entity;

import cn.yiueil.lang.instance.BaseEntity;
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
public class Tag implements BaseEntity<Integer>, Serializable {
    public Tag(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g_tag")
    @SequenceGenerator(name = "g_tag", sequenceName = "s_tag", allocationSize = 1)
    private Integer id;

    private String guid;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer createUser;

    @Column(length = 30, columnDefinition = "标签名")
    private String name;
}
