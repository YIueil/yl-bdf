package cn.yiueil.query.impl;

import cn.yiueil.query.SQLBuilder;
import cn.yiueil.util.CollectionUtils;
import org.dom4j.Element;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author:YIueil
 * Date:2022/7/25 19:53
 * Description: 简单实现sql拼接
 */
public class SimpleSQLBuilder extends SQLBuilder {
    @Override
    public String buildMain(Element element) {
        return element.elementText("sql");
    }

    @Override
    public String buildFilter(Element element, Map<String, Object> filter) {
        StringBuilder sb = new StringBuilder();
        List<Element> filters = element.element("filters").elements("filter");
        if (CollectionUtils.isNotEmpty(filters)) {
            filters.forEach(e -> {
                if (filter.containsKey(e.attributeValue("name"))) {
                    sb.append(e.getText());
                }
            });
        }
        return sb.toString();
    }

    @Override
    public String buildEnd(Element element) {
        return element.elementText("endSql");
    }

    @Override
    public String buildCount(String sql) {
        return "select count(*) from (" + sql + ")t";
    }
}
