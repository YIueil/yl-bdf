package cn.yiueil.query;

import cn.yiueil.dto.DynamicQueryDTO;
import cn.yiueil.util.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * Author:YIueil
 * Date:2022/7/26 0:09
 * Description: 动态查询服务池
 */
public class DynamicQueryPool {
    public static final String DEFAULT_CONFIG_PATH;

    private static final Map<String, Map<String, DynamicQuery>> dynamicSqlMap = new HashMap<>();

    static {
        URL dynamicsql = DynamicQuery.class.getClassLoader().getResource("dynamicsql");
        if (dynamicsql != null) {
            File file = new File(dynamicsql.getFile());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (ArrayUtils.isNotEmpty(files)) {
                    for (File configFile : files) {
                        try {
                            Document document = XmlUtils.parse(configFile);
                            if (ParseUtils.getBoolean(document.getRootElement().attributeValue("cache"), false)) {
                                dynamicSqlMap.put(configFile.getName(), parseDynamicQuery(document, configFile));
                            }
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        DEFAULT_CONFIG_PATH = Objects.requireNonNull(dynamicsql).getFile();
    }

    private static Map<String, DynamicQuery> parseDynamicQuery(Document document, File configFile) {
        HashMap<String, DynamicQuery> resultMap = new HashMap<>();
        SQLBuilder sqlBuilder = SpringContextUtils.getBean(SQLBuilder.class);
        List<Node> configList = XmlUtils.selectNodes(document, "/root/yl:config", "yl");
        if (CollectionUtils.isNotEmpty(configList)) {
            for (Node node : configList) {
                if (node instanceof Element) {
                    Element element = (Element) node;
                    String configId = element.attributeValue("id");
                    DynamicQuery dynamicQuery = new DynamicQuery(configId, configFile.getName());
                    resultMap.put(configId, sqlBuilder.buildDynamicQuery(element, dynamicQuery));
                }
            }
        }
        return resultMap;
    }

    public static Optional<DynamicQuery> findDynamicSql(DynamicQueryDTO dynamicQueryDTO) {
        DynamicQuery dynamicQuery = null;
        if (dynamicSqlMap.containsKey(dynamicQueryDTO.getConfigFile())) {
            Map<String, DynamicQuery> stringDynamicQueryMap = dynamicSqlMap.get(dynamicQueryDTO.getConfigFile());
            dynamicQuery = stringDynamicQueryMap.get(dynamicQueryDTO.getConfigId());
        }
        return Optional.ofNullable(dynamicQuery);
    }
}
