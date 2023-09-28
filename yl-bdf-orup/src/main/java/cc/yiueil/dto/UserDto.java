package cc.yiueil.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * UserDto 用户 DTO
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/6/22 15:30
 * @see cc.yiueil.entity.UserEntity
 * @see cc.yiueil.vo.UserVo
 */
@Getter
@Setter
@ToString
@ApiModel(value = "用户数据传输对象")
public class UserDto implements Serializable {
    private Long id;
    private String guid;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "登陆名")
    private String loginName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "联系电话")
    private String phoneNumber;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;
    @ApiModelProperty(value = "个性签名")
    private String signature;
    @ApiModelProperty(value = "用户状态")
    private String state;
    @ApiModelProperty(value = "创建用户id")
    private Long createUserId;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;
    @ApiModelProperty(value = "扩展1")
    private String extendProperty1;
    @ApiModelProperty(value = "扩展2")
    private String extendProperty2;
    @ApiModelProperty(value = "扩展3")
    private String extendProperty3;
    @ApiModelProperty(value = "扩展4")
    private String extendProperty4;
}
