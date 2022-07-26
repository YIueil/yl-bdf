package cn.yiueil.query.impl;

import cn.yiueil.query.ConfigResolver;
import cn.yiueil.query.DynamicQuery;
import cn.yiueil.query.instance.DynamicQueryInst;
import cn.yiueil.util.CollectionUtils;
import cn.yiueil.util.MapUtils;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/7/25 19:53
 * Description: 简单实现sql拼接 存在sql注入风险
 */
public class SimpleConfigResolver implements ConfigResolver {
    @Override
    public String buildMixSql(Element element) {
        return element.elementText("sql");
    }

    @Override
    public String buildEndSql(Element element) {
        return element.elementText("endSql");
    }

    @Override
    public Map<String, Object> buildFilters(Element element) {
        Map<String, Object> filterMap = new HashMap<>();
        Element filters = element.element("filters");
        if (filters == null) {
            return filterMap;
        }
        List<Element> filterList = filters.elements("filter");
        if (CollectionUtils.isNotEmpty(filterList)) {
            filterList.forEach(filter -> filterMap.put(filter.attributeValue("name"), filter.getText()));
        }
        return filterMap;
    }

    @Override
    public Map<String, Object> buildParams(Element element) {
        Map<String, Object> elementMap = new HashMap<>();
        Element params = element.element("params");
        if (params == null) {
            return elementMap;
        }
        List<Element> paramList = params.elements("param");
        if (CollectionUtils.isNotEmpty(paramList)) {
            paramList.forEach(param -> elementMap.put(param.attributeValue("name"), param.attributeValue("default")));
        }
        return elementMap;
    }

    @Override
    public DynamicQueryInst constructInst(DynamicQuery dynamicQuery, Map<String, Object> parameters) {
        DynamicQueryInst dynamicQueryInst = DynamicQueryInst.of(dynamicQuery);
        buildParameters(dynamicQueryInst, parameters); // 对于没有传入参数的, 取默认值
        dynamicQueryInst.setSql(
                buildSql(dynamicQueryInst.getMixSql(), dynamicQueryInst.getEndSql(),
                dynamicQueryInst.getFilters(), parameters));
        dynamicQueryInst.setCountSql(buildCount(dynamicQueryInst.getSql()));
        return dynamicQueryInst;
    }

    public void buildParameters(DynamicQueryInst dynamicQueryInst, Map<String, Object> parameters) {
        HashMap<String, Object> newParameters = new HashMap<>(parameters);
        Map<String, Object> params = dynamicQueryInst.getParams();
        if (MapUtils.isNotEmpty(params)) {
            for (String paramKey : params.keySet()) {
                if (!parameters.containsKey(paramKey)) {
                    newParameters.put(paramKey, params.get(paramKey));
                }
            }
        }
        dynamicQueryInst.setParameters(newParameters);
    }

    public String buildSql(String mixSql, String endSql, Map<String, Object> filters, Map<String, Object> parameters) {
        return mixSql + assembleCondition(filters, parameters) + endSql;
    }

    private String assembleCondition(Map<String, Object> filters, Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        if (MapUtils.isNotEmpty(filters) && MapUtils.isNotEmpty(params)) {
            for (String paramKey : params.keySet()) {
                if (filters.containsKey(paramKey)) {
                    sb.append(filters.get(paramKey));
                }
            }
        }
        return sb.toString();
    }

    public String buildCount(String sql) {
        return "select count(*) from (" + sql + ")t";
    }
}
