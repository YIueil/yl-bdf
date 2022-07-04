package cn.yiueil.lang.tree.parser;

import cn.yiueil.lang.instance.Parser;
import cn.yiueil.lang.tree.Tree;
import cn.yiueil.lang.tree.TreeNode;
import cn.yiueil.util.MapUtils;

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
