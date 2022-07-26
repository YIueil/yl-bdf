package cn.yiueil.query;

import cn.yiueil.query.instance.DynamicQueryInst;
import cn.yiueil.util.CollectionUtils;
import cn.yiueil.util.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/7/26 11:04
 * Description: 配置解析器
 */
public interface ConfigResolver {
    /**
     * 解析整个文档生成动态查询节点
     * @param file 配置文档节点
     * @return 动态查询对象Map
     */
    default DynamicQueryNode buildDynamicQueryNode(File file) {
        try {
            DynamicQueryNode dynamicQueryNode = new DynamicQueryNode(file);
            Document document = XmlUtils.parse(file);
            dynamicQueryNode.setDynamicQueryMap(buildDynamicQueryNode(document));
            return dynamicQueryNode;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("配置节点无效! 文件有误:" + file.getName());
    }

    /**
     * 解析整个文档生成动态查询节点
     * @param document 配置文档节点
     * @return 动态查询对象Map
     */
    default Map<String, DynamicQuery> buildDynamicQueryNode(Document document) {
        Map<String, DynamicQuery> dynamicQueryNode = new HashMap<>();
        List<Node> nodes = XmlUtils.selectNodes(document, "/root/yl:config", "yl");
        if (CollectionUtils.isNotEmpty(nodes)) {
            for (Node node : nodes) {
                DynamicQuery dynamicQuery = new DynamicQuery();
                if (node instanceof Element) {
                    Element element = (Element) node;
                    buildDynamicQuery(element, dynamicQuery);
                    dynamicQueryNode.put(element.attributeValue("id"), dynamicQuery);
                }
            }
        }
        return dynamicQueryNode;
    }

    /**
     * 构造动态查询服务
     *
     * @param element      配置节点
     * @param dynamicQuery 动态查询对象
     */
    default void buildDynamicQuery(Element element, DynamicQuery dynamicQuery) {
        dynamicQuery.setMixSql(buildMixSql(element));
        dynamicQuery.setEndSql(buildEndSql(element));
        dynamicQuery.setParams(buildParams(element));
        dynamicQuery.setFilters(buildFilters(element));
    }

    /**
     * 构建符合jpa执行规范的sql
     * @param mixSql 主体sql
     * @param endSql 尾部sql
     * @param filters 参数集合
     * @param parameters 查询参数列表
     * @return 符合jpa执行规范的sql
     */
    String buildSql(String mixSql, String endSql, Map<String, Object> filters, Map<String, Object> parameters);

    /**
     * 构造过滤参数列表
     * @param element 配置节点
     * @return 过滤参数列表Map
     */
    Map<String, Object> buildFilters(Element element);

    /**
     * 构造内嵌参数列表
     * @param element 配置节点
     * @return 内嵌参数集合
     */
    Map<String, Object> buildParams(Element element);

    /**
     * 构造sql主体部分
     * @param element 配置节点
     * @return 主体
     */
    String buildMixSql(Element element);


    /**
     * 构造sql尾部部分
     * @param element 配置节点
     * @return 尾部SQL
     */
    String buildEndSql(Element element);

    /**
     * 构造实际的查询参数列表(拼接默认值)
     * @param dynamicQueryInst 查动态询实例
     * @param parameters 查询参数列表
     */
    void buildParameters(DynamicQueryInst dynamicQueryInst, Map<String, Object> parameters);

    /**
     * 构造查询实例
     *
     * @param dynamicQuery 动态查询
     * @param parameters 查询参数列表
     * @return 主题 + 过滤
     */
    DynamicQueryInst constructInst(DynamicQuery dynamicQuery, Map<String, Object> parameters);
}
