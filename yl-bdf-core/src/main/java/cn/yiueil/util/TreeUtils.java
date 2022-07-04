package cn.yiueil.util;

import cn.yiueil.lang.tree.Tree;
import cn.yiueil.lang.tree.TreeBuilder;
import cn.yiueil.lang.tree.TreeNode;
import cn.yiueil.lang.tree.TreeNodeConfig;
import cn.yiueil.lang.tree.parser.TreeParser;

import java.util.List;

/**
 * Author:YIueil
 * Date:2022/7/4 21:38
 * Description: todo 树结构操作工具
 */
public class TreeUtils {

    /**
     * 构建树结构
     *
     * @param <T>          id类型
     * @param treeNodeList 扁平集合结构
     * @param rootId       根节点id
     * @return 构建好的树结构
     */
    public static <T> List<Tree<T>> build(List<TreeNode<T>> treeNodeList, T rootId) {
        return TreeBuilder.of(rootId, TreeNodeConfig.DEFAULT_CONFIG)
                .append(treeNodeList, new TreeParser<>())
                .buildList();
    }
}
