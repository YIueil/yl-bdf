package cc.yiueil.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * OrgDto org数据传输类
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/10/4 15:03
 * @see cc.yiueil.entity.OrgEntity
 */
@Getter
@Setter
@ToString
@ApiModel(value = "机构数据传输对象")
public class OrgDto {
    private Long id;
    private String guid;
    @ApiModelProperty(value = "机构名称")
    private String name;
    @ApiModelProperty(value = "机构代码")
    private String code;
    @ApiModelProperty(value = "类型(单位、部门)")
    private String type;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "父节点id")
    private Long parentId;
}
