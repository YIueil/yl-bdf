package cn.yiueil.dto;

import cn.yiueil.query.DynamicQueryConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/7/6 16:44
 * Description: 动态查询参数接收
 */
@Getter
@Setter
@ApiModel(value = "动态查询参数")
public class DynamicQueryDTO {
    @NotNull
    @ApiModelProperty(value = "查询服务配置的id")
    private String configId;

    @ApiModelProperty(value = "查询服务配置的name")
    private String configName;

    @ApiModelProperty(value = "查询服务所在的路径")
    private String configPath;

    @ApiModelProperty(value = "查询的过滤条件")
    private Map<String, Object> filter = new HashMap<>();

    @ApiModelProperty(value = "查询配置")
    private DynamicQueryConfig config;
}
