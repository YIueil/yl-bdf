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
    public DynamicQuery(String configId, String configFile) {
        this.configId = configId;
        this.configFile = configFile;
    }

    private String configId;

    private String configPath;

    private String configFile;

    private String sql;

    private String endSql;

    private String countSql;

    private List<String> params;

    private Map<String, String> filters;

    private DynamicQueryConfig config;
}
