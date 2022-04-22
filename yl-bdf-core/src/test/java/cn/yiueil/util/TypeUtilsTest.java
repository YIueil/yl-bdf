package cn.yiueil.util;

import cn.yiueil.lang.reflect.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TypeUtilsTest {

    @Test
    void testGetClass() {
        Class<?> aClass = TypeUtils.getClass(new TypeReference<ArrayList<String>>() {}.getType());
        System.out.println(aClass);
    }
}