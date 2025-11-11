package cc.yiueil.entity;

import cc.yiueil.instance.VerifyCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * VerifyCodeEntity 验证码实体
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/19 22:43
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "verify_code", schema = "yl_pcc")
public class VerifyCodeEntity implements VerifyCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator")
    @SequenceGenerator(name = "generator", schema = "yl_pcc", sequenceName = "s_verify_code", allocationSize = 1)
    private Long id;
    private String guid;
    private String code;
    private String useType;
    private String sendType;
    private String status;
    private LocalDateTime expire;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Long createUserId;
}
