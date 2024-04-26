package cc.yiueil.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * PermissionDto 权限数据传输类
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/9/10 1:53
 */
@Getter
@Setter
@ToString
@ApiModel(value = "权限数据传输类")
public class PermissionDto {
    private Long id;
    private String guid;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "唯一标识名称")
    private String rightName;
    @ApiModelProperty(value = "描述")
    private String description;
    private Long parentId;
}
