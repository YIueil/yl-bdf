package cc.yiueil.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * LinkDto 链接数据传输对象
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/2 22:42
 */
@Getter
@Setter
@ToString
@ApiModel(value = "链接数据传输对象")
public class LinkDto {
    private Long id;
    private String guid;
    @ApiModelProperty(value = "链接名称")
    private String name;
    @ApiModelProperty(value = "链接图标")
    private String iconUrl;
    @ApiModelProperty(value = "链接地址")
    private String url;
    @ApiModelProperty(value = "链接用户")
    private Long userId;
}
