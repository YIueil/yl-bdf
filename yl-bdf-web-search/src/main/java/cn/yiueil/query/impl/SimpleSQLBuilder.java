package cn.yiueil.query.impl;

import cn.yiueil.data.impl.JpaBaseDao;
import cn.yiueil.vo.PageVo;
import cn.yiueil.query.DynamicQuery;
import cn.yiueil.query.SQLBuilder;
import cn.yiueil.util.CollectionUtils;
import cn.yiueil.util.MapUtils;
import org.dom4j.Element;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author:YIueil
 * Date:2022/7/25 19:53
 * Description: 简单实现sql拼接
 */
public class SimpleSQLBuilder extends SQLBuilder {
    private JpaBaseDao jpaBaseDao;

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
    public String buildFilter(DynamicQuery dynamicQuery, Map<String, Object> filter) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> filters = dynamicQuery.getFilters();
        if (MapUtils.isNotEmpty(filters)) {
            Set<String> keySet = filters.keySet();
            keySet.forEach(key->{
                if (filter.containsKey(key)) {
                    sb.append(filters.get(key));
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

    @Override
    public Map<String, String> getFilters(Element element) {
        Map<String, String> filterMap = new HashMap<>();
        List<Element> filters = element.element("filters").elements("filter");
        if (CollectionUtils.isNotEmpty(filters)) {
            filters.forEach(filter->{
                filterMap.put(filter.attributeValue("name"), filter.getText());
            });
        }
        return filterMap;
    }

    @Override
    public List<String> getParams(Element element) {
        List<Element> params = element.element("params").elements("param");
        if (CollectionUtils.isNotEmpty(params)) {
            return params.stream().map(p -> p.attributeValue("name")).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public void buildPageVo(String sql, Map<String, Object> filter, PageVo pageVo) {
        pageVo.setBody(jpaBaseDao.sqlAsMap(sql, filter, pageVo.getPageIndex(), pageVo.getPageSize()));
        pageVo.setItemCounts(jpaBaseDao.countSize(buildCount(sql), filter));
        pageVo.setPageTotal((pageVo.getItemCounts() / pageVo.getPageSize()) + 1);
    }

    public JpaBaseDao getJpaBaseDao() {
        return jpaBaseDao;
    }

    public void setJpaBaseDao(JpaBaseDao jpaBaseDao) {
        this.jpaBaseDao = jpaBaseDao;
    }
}
