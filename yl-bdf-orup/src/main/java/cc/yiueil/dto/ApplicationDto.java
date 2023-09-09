package cc.yiueil.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ApplicationDto
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/9/10 3:50
 */
@Getter
@Setter
@ToString
@ApiModel(value = "应用数据传输对象")
public class ApplicationDto {
    private Long id;
    private String guid;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "访问地址")
    private String url;
}
