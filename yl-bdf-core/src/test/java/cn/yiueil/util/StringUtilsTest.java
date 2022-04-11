package cn.yiueil.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void isBlank() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void reverse() {
        String str = "12345678";
        System.out.println(StringUtils.reverse(str));
    }

    @Test
    void fill() {
        String str = "YIueil";
        System.out.println(StringUtils.fill(str, '-', 16, true));
        System.out.println(StringUtils.fill(str, '?', 16, false));
    }

    @Test
    void similar() {
        String str1 = "YIueil1234567";
        String str2 = "yiueil1234567";
        System.out.println(StringUtils.similar(str1, str2));
    }

    @Test
    void format() {
        String str1 = "YIueil${user}12${password}34567";
        HashMap<Object, Object> map = new HashMap<>();
        map.put("user", "张三");
        map.put("password", null);
    }
}