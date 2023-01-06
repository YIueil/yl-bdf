package cc.yiueil.query.instance;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/7/26 14:37
 * Description: 动态查询实例对象 通过DynamicQuery模板和filter参数构建
 */
@Getter
@Setter
public class DynamicQueryInst extends DynamicQuery {
    private DynamicQueryInst() {}

    public static DynamicQueryInst of(DynamicQuery dynamicQuery) {
        DynamicQueryInst dynamicQueryInst = new DynamicQueryInst();
        dynamicQueryInst.setConfigId(dynamicQuery.getConfigId());
        dynamicQueryInst.setConfigPath(dynamicQuery.getConfigPath());
        dynamicQueryInst.setConfigFile(dynamicQuery.getConfigFile());
        dynamicQueryInst.setMixSql(dynamicQuery.getMixSql());
        dynamicQueryInst.setEndSql(dynamicQuery.getEndSql());
        dynamicQueryInst.setFilters(dynamicQuery.getFilters());
        dynamicQueryInst.setParams(dynamicQuery.getParams());
        return dynamicQueryInst;
    }

    private String sql;

    private String countSql;

    private Map<String, Object> parameters;
}
