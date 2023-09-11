package cc.yiueil.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * FunctionDto 应用功能数据传输对象
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/9/10 4:22
 */
@Getter
@Setter
@ToString
@ApiModel(value = "应用功能数据传输对象")
public class FunctionDto {
    private Long id;
    private String guid;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "唯一标识名称")
    private String rightName;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "资源地址")
    private String url;
    @ApiModelProperty(value = "资源类型")
    private String type;
    @ApiModelProperty(value = "方法")
    private String method;
    @ApiModelProperty(value = "父节点id")
    private Long parentId;
    @ApiModelProperty(value = "应用id")
    private Long applicationId;
}
