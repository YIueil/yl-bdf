package cn.yiueil.query;


import org.dom4j.Element;
import org.dom4j.Node;

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
     * 构造sql尾部部分
     * @param element 配置节点
     * @return 尾部SQL
     */
    public abstract String buildEnd(Element element);
}
