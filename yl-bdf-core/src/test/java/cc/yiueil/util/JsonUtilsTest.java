package cc.yiueil.util;

import cc.yiueil.lang.tree.Tree;
import cc.yiueil.lang.tree.TreeNode;
import cc.yiueil.lang.tree.TreeNodeConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class JsonUtilsTest {

    @Test
    void toJSONString() throws JsonProcessingException {
        ObjectMapper instance = JsonUtils.getInstance();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(true);
        objects.add("2");
        objects.add("");
        String string = instance.writeValueAsString(objects);
        JsonNode jsonNode = instance.readTree(string);
        System.out.println();
        System.out.println(jsonNode.get(0).intValue());
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
        String string = JsonUtils.toJsonString(objectTree);
        System.out.println(string);
        TreeNode<Integer> parse = JsonUtils.parse(TreeNode.class, string);
        System.out.println(parse);
    }
}