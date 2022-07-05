package cn.yiueil.util;

import cn.yiueil.lang.tree.Tree;
import cn.yiueil.lang.tree.TreeNode;
import cn.yiueil.lang.tree.TreeNodeConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONUtilsTest {

    @Test
    void toJSONString() {
        ObjectMapper instance = JSONUtils.getInstance();
        ObjectMapper instance1 = JSONUtils.getInstance();
        System.out.println();
    }

    @Test
    void getInstance() {
    }

    @Test
    void testToJSONString() {
    }

    @Test
    @SuppressWarnings("unchecked")
    void parse() {
        Tree<Object> objectTree = new Tree<>(new TreeNodeConfig());
        String string = JSONUtils.toJSONString(objectTree);
        System.out.println(string);
        TreeNode<Integer> parse = JSONUtils.parse(TreeNode.class, string);
        System.out.println(parse);
    }
}