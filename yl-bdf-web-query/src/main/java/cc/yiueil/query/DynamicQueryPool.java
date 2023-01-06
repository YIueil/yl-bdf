package cc.yiueil.query;

import cc.yiueil.dto.DynamicQueryDTO;
import cc.yiueil.query.instance.DynamicQuery;
import cc.yiueil.util.ArrayUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Author:YIueil
 * Date:2022/7/26 0:09
 * Description: 动态查询资源池 资源池包含多个节点，一个文件一个节点
 */
@Component
public class DynamicQueryPool implements InitializingBean {
    @Autowired
    private ConfigResolver configResolver; // 处理器

    private static final String DEFAULT_CONFIG_PATH = "dynamicsql";

    private String[] locations = {DEFAULT_CONFIG_PATH}; // 动态查询目录

    private final Map<String, DynamicQueryNode> dynamicSqlMap = new HashMap<>();

    /**
     * 获取动态查询节点
     * @param dynamicQueryDTO 动态查询数据传输对象
     * @return 动态查询节点
     */
    public Optional<DynamicQueryNode> findDynamicNode(DynamicQueryDTO dynamicQueryDTO) {
        DynamicQueryNode dynamicQueryNode = null;
        if (dynamicSqlMap.containsKey(dynamicQueryDTO.getFullPath())) {
            dynamicQueryNode = dynamicSqlMap.get(dynamicQueryDTO.getFullPath());
        }
        return Optional.ofNullable(dynamicQueryNode);
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (String location : locations) {
            URL dynamicsql = DynamicQuery.class.getClassLoader().getResource(location);
            if (dynamicsql != null) {
                File file = new File(dynamicsql.getFile());
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    if (ArrayUtils.isNotEmpty(files)) {
                        for (File configFile : files) {
                            dynamicSqlMap.put(
                                    DEFAULT_CONFIG_PATH + "/" + configFile.getName(),
                                    configResolver.buildDynamicQueryNode(configFile)
                            );
                        }
                    }
                }
            }
        }
    }
}
