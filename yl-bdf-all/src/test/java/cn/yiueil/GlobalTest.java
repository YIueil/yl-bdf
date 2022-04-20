package cn.yiueil;

import cn.yiueil.util.ParseUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

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
    public void test1(){
        System.out.println(ParseUtils.getString(1, ""));
    }

    /**
     * 测试：
     */
    @Test
    public void test2() throws ParseException {
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:sss").parse("2020-01-01"));
        Object o = 1650010801000L;
        Date date = ParseUtils.get(Date.class, o, null);
        System.out.println(date);
    }
}
