package cn.yiueil.query;


import cn.yiueil.vo.PageVo;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.List;
import java.util.Map;

public abstract class SQLBuilder {

    /**
     * 构建符合jpa执行标准的sql语句
     * @param node 配置节点
     * @param filter 过滤条件集合
     * @return 参数化的jpa标准sql语句
     */
    public String build(Node node, Map<String, Object> filter) {
        if (node instanceof Element) {
            Element element = (Element) node;
            return buildMain(element) + buildFilter(element, filter) + buildEnd(element);
        }
        throw new RuntimeException("传入的节点不是Element节点!");
    }

    /**
     * 构建符合jpa执行标准的sql语句
     * @param dynamicQuery 动态查询配置
     * @param filter 过滤条件集合
     * @return 参数化的jpa标准sql语句
     */
    public String build(DynamicQuery dynamicQuery, Map<String, Object> filter) {
        return dynamicQuery.getSql() + buildFilter(dynamicQuery, filter) + dynamicQuery.getEndSql();
    }

    /**
     * 构造动态查询服务
     * @param node 配置节点
     * @param dynamicQuery 动态查询对象
     * @return 动态查询对象
     */
    public DynamicQuery buildDynamicQuery(Node node, DynamicQuery dynamicQuery){
        if (node instanceof Element) {
            Element element = (Element) node;
            dynamicQuery.setSql(buildMain(element));
            dynamicQuery.setEndSql(buildEnd(element));
            dynamicQuery.setParams(getParams(element));
            dynamicQuery.setFilters(getFilters(element));
        }
        return dynamicQuery;
    }

    /**
     * 构造过滤参数列表
     * @param element 配置节点
     * @return 过滤参数列表Map
     */
    public abstract Map<String, String> getFilters(Element element);

    /**
     * 构造内嵌参数列表
     * @param element 配置节点
     * @return
     */
    public abstract List<String> getParams(Element element);

    /**
     * 构造sql主体部分
     * @param element 配置节点
     * @return 主体
     */
    public abstract String buildMain(Element element);

    /**
     * 构造过滤参数
     * @param element 配置节点
     * @param filter 过滤参数列表
     * @return 主题 + 过滤
     */
    public abstract String buildFilter(Element element, Map<String, Object> filter);

    /**
     * 构造过滤参数
     * @param dynamicQuery 动态查询对象
     * @param filter 过滤参数列表
     * @return 主题 + 过滤
     */
    public abstract String buildFilter(DynamicQuery dynamicQuery, Map<String, Object> filter);

    /**
     * 构造sql尾部部分
     * @param element 配置节点
     * @return 尾部SQL
     */
    public abstract String buildEnd(Element element);

    /**
     * 构造Count语句
     * @param sql 可执行的sql语句
     * @return count语句
     */
    public abstract String buildCount(String sql);

    /**
     * 构造分页对象返回
     * @param sql 原始查询sql语句
     * @param filter 过滤参数列表
     */
    public abstract void buildPageVo(String sql, Map<String, Object> filter, PageVo pageVo);
}
