package cc.yiueil.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RoleDto 角色数据传输类
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/9/10 1:55
 */
@Getter
@Setter
@ToString
@ApiModel(value = "角色数据传输对象")
public class RoleDto {
    private Long id;
    private String guid;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "已关联到功能")
    private Boolean hasFunction;
    @ApiModelProperty(value = "已关联到权限")
    private Boolean hasPermission;
    private Long parentId;
}
