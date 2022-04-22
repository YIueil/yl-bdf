package cn.yiueil.util;

import cn.yiueil.lang.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TypeUtilsTest {

    @Test
    void testGetClass() {
        Class<?> aClass = TypeUtils.getClass(new TypeReference<ArrayList<String>>() {}.getType());
        System.out.println(aClass);
    }
}