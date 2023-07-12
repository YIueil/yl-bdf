package cc.yiueil.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * UserDTO 用户 DTO
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/6/22 15:30
 */
@Getter
@Setter
@ToString
public class UserDTO implements Serializable {
    private Long id;
    private String guid;
    private String userName;
    private String loginName;
    private String password;
    private String phoneNumber;
    private String email;
    private String state;
    private Long createUserId;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private String extendProperty1;
    private String extendProperty2;
    private String extendProperty3;
    private String extendProperty4;
}
