package cc.yiueil.vo;

import cc.yiueil.dto.PermissionDto;
import cc.yiueil.dto.RoleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * UserVo 用户视图对象
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/9/10 1:40
 */
@Getter
@Setter
@ToString
@ApiModel(value = "用户视图对象")
public class UserVo {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "guid")
    private String guid;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "登陆名")
    private String loginName;
    @ApiModelProperty(value = "联系电话")
    private String phoneNumber;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;
    @ApiModelProperty(value = "扩展1")
    private String extendProperty1;
    @ApiModelProperty(value = "扩展2")
    private String extendProperty2;
    @ApiModelProperty(value = "扩展3")
    private String extendProperty3;
    @ApiModelProperty(value = "扩展4")
    private String extendProperty4;
    @ApiModelProperty(value = "角色集合")
    private List<RoleDto> roleDtoList;
    @ApiModelProperty(value = "权限集合")
    private List<PermissionDto> permissionDtoList;
}
