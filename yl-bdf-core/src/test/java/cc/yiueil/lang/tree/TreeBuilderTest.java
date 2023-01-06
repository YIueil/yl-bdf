package cc.yiueil.lang.tree;

import cc.yiueil.lang.tree.parser.TreeParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class TreeBuilderTest {
    static List<TreeNode<Long>> all_menu = new ArrayList<>();
    static List<TreeNode<String>> custom_menu = new ArrayList<>();

    static {
        /*
         * root
         *    /module-A
         *    	   /module-A-menu-1
         *    /module-B
         *    	   /module-B-menu-1
         *    	   /module-B-menu-2
         */
        all_menu.add(new TreeNode<>(1L, 0L, "root", 0L, null));
        all_menu.add(new TreeNode<>(2L, 1L, "module-A", 0L, null));
        all_menu.add(new TreeNode<>(3L, 1L, "module-B", 0L, null));
        all_menu.add(new TreeNode<>(4L, 2L, "module-A-menu-1", 0L, null));
        all_menu.add(new TreeNode<>(5L, 3L, "module-B-menu-1", 0L, null));
        all_menu.add(new TreeNode<>(6L, 3L, "module-B-menu-2", 0L, null));

        HashMap<String, Object> extra = new HashMap<>();
        extra.put("extra1", "extra1");
        extra.put("extra2", "extra2");
        extra.put("extra3", "extra3");
        custom_menu.add(new TreeNode<>("1L", "0L", "root", 0L, extra));
        custom_menu.add(new TreeNode<>("2L", "1L", "module-A", 0L, extra));
        custom_menu.add(new TreeNode<>("3L", "1L", "module-B", 0L, extra));
        custom_menu.add(new TreeNode<>("4L", "2L", "module-A-menu-1", 0L, extra));
        custom_menu.add(new TreeNode<>("5L", "3L", "module-B-menu-1", 0L, extra));
        custom_menu.add(new TreeNode<>("6L", "3L", "module-B-menu-2", 0L, extra));

    }

    @Test
    void build() {
        Tree<Long> build = TreeBuilder.of(0L)
                .append(all_menu, new TreeParser<>()).build();
        System.out.println();
    }

    @Test
    void build2() {
        List<Tree<String>> trees = TreeBuilder
                .of("0L", new TreeNodeConfig("idKey", "parentIdKey", "weightKey", "nameKey", "childrenKey"))
                .append(custom_menu, new TreeParser<>()).buildList();

        System.out.println();
    }
}