package cc.yiueil.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UserLoginDto 用户登陆对象
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/9/9 23:42
 */
@Getter
@Setter
@ToString
@ApiModel(value = "用户登陆对象")
public class UserLoginDto {
    @ApiModelProperty(value = "登陆名")
    private String loginName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "联系电话")
    private String phoneNumber;
    @ApiModelProperty(value = "邮箱")
    private String email;
}
