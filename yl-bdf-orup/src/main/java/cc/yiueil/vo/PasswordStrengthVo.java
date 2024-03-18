package cc.yiueil.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * PasswordStrengthVo 密码健壮性视图对象
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/18 17:29
 */
@Getter
@Setter
@ToString
@ApiModel(value = "密码健壮性视图对象")
public class PasswordStrengthVo {
    @ApiModelProperty(value = "密码强度")
    private String passwordStrengthLevel;
    @ApiModelProperty(value = "预估破解时间")
    private String expectedCrackingTime;
}
