package cn.yiueil.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/7/6 17:34
 * Description: 动态查询
 */
@Getter
@Setter
public class DynamicQuery {
    String configId;

    String configPath;

    String configFile;

    private String mixSql;

    private String endSql;

    private Map<String, Object> params;

    private Map<String, Object> filters;
}
