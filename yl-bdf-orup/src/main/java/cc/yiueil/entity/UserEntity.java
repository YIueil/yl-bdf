package cc.yiueil.entity;

import cc.yiueil.lang.instance.BaseEntity;
import cc.yiueil.lang.instance.HasGuid;
import cc.yiueil.lang.instance.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Author:YIueil
 * Date:2023/1/12 11:09
 * Description: 用户数据传输对象
 */
@Getter
@Setter
@Entity
@Table(name = "t_user")
public class UserEntity implements User<Long>, BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "s_user", allocationSize = 1)
    private Long id;
    private String guid;
    private String userName;
    private String loginName;
    private String password;
    private Long createUser;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
}
