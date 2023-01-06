package cc.yiueil.lang.tree.parser;

import cc.yiueil.lang.instance.Parser;
import cc.yiueil.lang.tree.Tree;
import cc.yiueil.lang.tree.TreeNode;
import cc.yiueil.util.MapUtils;

import java.util.Map;

public class TreeParser<T> implements Parser<TreeNode<T>, Tree<T>> {
    @Override
    public void parse(TreeNode<T> treeNode, Tree<T> tree) {
        tree.setId(treeNode.getId());
        tree.setParentId(treeNode.getParentId());
        tree.setWeight(treeNode.getWeight());
        tree.setName(treeNode.getName());
        //扩展字段
        final Map<String, Object> extra = treeNode.getExtra();
        if (MapUtils.isNotEmpty(extra)) {
            extra.forEach(tree::putExtra);
        }
    }
}
