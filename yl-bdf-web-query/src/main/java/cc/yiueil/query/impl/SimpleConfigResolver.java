package cc.yiueil.query.impl;

import cc.yiueil.query.ConfigResolver;
import cc.yiueil.query.instance.DynamicQuery;
import cc.yiueil.query.instance.DynamicQueryInst;
import cc.yiueil.query.instance.Filter;
import cc.yiueil.query.instance.Param;
import cc.yiueil.util.CollectionUtils;
import cc.yiueil.util.MapUtils;
import cc.yiueil.util.ParseUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/7/25 19:53
 * Description: 简单实现sql拼接 存在sql注入风险
 */
@Component
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
    public Map<String, Filter> buildFilters(Element element) {
        Map<String, Filter> filterMap = new HashMap<>();
        Element filters = element.element("filters");
        if (filters == null) {
            return filterMap;
        }
        List<Element> filterElementList = filters.elements("filter");
        if (CollectionUtils.isNotEmpty(filterElementList)) {
            filterElementList.forEach(filterElement -> {
                Filter filter = new Filter();
                filter.setName(filterElement.attributeValue("name"));
                filter.setLeft(filterElement.attributeValue("left"));
                filter.setRight(filterElement.attributeValue("right"));
                filter.setText(filterElement.getText());
                filterMap.put(filter.getName(), filter);
            });
        }
        return filterMap;
    }

    @Override
    public Map<String, Param> buildParams(Element element) {
        Map<String, Param> elementMap = new HashMap<>();
        Element params = element.element("params");
        if (params == null) {
            return elementMap;
        }
        List<Element> paramElementList = params.elements("param");
        if (CollectionUtils.isNotEmpty(paramElementList)) {
            paramElementList.forEach(paramElement -> {
                Param param = new Param();
                param.setName(paramElement.attributeValue("name"));
                param.setDefaultValue(paramElement.attributeValue("default"));
                param.setText(paramElement.getText());
                elementMap.put(param.getName(), param);
            });
        }
        return elementMap;
    }

    @Override
    public DynamicQueryInst constructInst(DynamicQuery dynamicQuery, Map<String, Object> parameters) {
        DynamicQueryInst dynamicQueryInst = DynamicQueryInst.of(dynamicQuery);
        buildParameters(dynamicQueryInst, parameters); // 对于没有传入参数的, 取默认值
        concatFilterExtra(dynamicQueryInst, parameters);

        dynamicQueryInst.setSql(buildSql(dynamicQueryInst.getMixSql(), dynamicQueryInst.getEndSql(),
                dynamicQueryInst.getFilters(), parameters));
        dynamicQueryInst.setCountSql(buildCount(dynamicQueryInst.getSql()));
        return dynamicQueryInst;
    }

    public void buildParameters(DynamicQueryInst dynamicQueryInst, Map<String, Object> parameters) {
        setDefaultParamValue(dynamicQueryInst, parameters, parameters);
    }

    private void concatFilterExtra(DynamicQueryInst dynamicQueryInst, Map<String, Object> parameters) {
        Map<String, Object> newParameters = new HashMap<>();
        Map<String, Filter> filters = dynamicQueryInst.getFilters();
        if (MapUtils.isNotEmpty(filters)) {
            for (String paramKey : filters.keySet()) {
                if (parameters.containsKey(paramKey)) {
                    Filter filter = filters.get(paramKey);
                    newParameters.put(paramKey,
                            ParseUtils.getString(filter.getLeft(), "")
                            + ParseUtils.getString(parameters.get(paramKey), "")
                            + ParseUtils.getString(filter.getRight(), ""));
                }
            }
        }
        dynamicQueryInst.setParameters(newParameters);
    }

    private void setDefaultParamValue(DynamicQueryInst dynamicQueryInst, Map<String, Object> parameters, Map<String, Object> newParameters) {
        Map<String, Param> params = dynamicQueryInst.getParams();
        if (MapUtils.isNotEmpty(params)) {
            for (String paramKey : params.keySet()) {
                if (!parameters.containsKey(paramKey)) {
                    newParameters.put(paramKey, params.get(paramKey));
                }
            }
        }
    }

    public String buildSql(String mixSql, String endSql, Map<String, Filter> filters, Map<String, Object> parameters) {
        return mixSql + assembleCondition(filters, parameters) + endSql;
    }

    private String assembleCondition(Map<String, Filter> filters, Map<String, Object> params) {
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
