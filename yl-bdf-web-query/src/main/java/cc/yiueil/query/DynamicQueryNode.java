package cc.yiueil.query;

import cc.yiueil.query.instance.DynamicQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/7/26 11:10
 * Description: 动态查询节点 每个文件生成一个节点, 每个节点持有查询服务
 */
@Getter
@Setter
@NoArgsConstructor
public class DynamicQueryNode {
    private Map<String, DynamicQuery> dynamicQueryMap;

    private String filename;

    private Long lastModified;

    public DynamicQueryNode(File file) {
        this.filename = file.getName();
        this.lastModified = file.lastModified();
    }
}
