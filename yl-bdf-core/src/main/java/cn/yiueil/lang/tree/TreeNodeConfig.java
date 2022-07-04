package cn.yiueil.lang.tree;

import lombok.*;

import java.io.Serializable;

/**
 * Author:YIueil
 * Date:2022/7/4 22:20
 * Description: 树属性配置
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TreeNodeConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final TreeNodeConfig DEFAULT_CONFIG = new TreeNodeConfig();

    // 属性名配置字段
    private String idKey = "id";
    private String parentIdKey = "parentId";
    private String weightKey = "weight";
    private String nameKey = "name";
    private String childrenKey = "children";
}
