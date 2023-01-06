package cc.yiueil.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Author:YIueil
 * Date:2022/7/6 17:07
 * Description: 动态查询配置
 */
@Getter
@Setter
@ApiModel(value = "动态查询配置")
public class DynamicQueryConfig {
    @ApiModelProperty(value = "结果集转大写")
    private Boolean toUpperCase = false;
}
