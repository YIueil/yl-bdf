package cn.yiueil;

import cn.yiueil.util.ParseUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author:YIueil
 * Date:2022/4/15 10:18
 * Description: 全局测试类
 */
public class GlobalTest {
    /**
     * 测试：
     */
    @Test
    public void test1() {
        List<Object> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.add(false);
        list.add(1);
        list.add(2.0);
        list.add("3");
        List<String> result = ParseUtils.getList(list, String.class);
        System.out.println(result);
    }

    /**
     * 测试：
     */
    @Test
    public void test2() {
        String str = "[1,23,456]";
        List<String> result = ParseUtils.getList(str, String.class);
        System.out.println(result);
    }

    /**
     * 测试：
     */
    @Test
    public void test3(){

    }
}
