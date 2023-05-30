package cc.yiueil.util;

import cc.yiueil.lang.reflect.AbstractTypeReference;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TypeUtilsTest {

    @Test
    void testGetClass() {
        Class<?> aClass = TypeUtils.getClass(new AbstractTypeReference<ArrayList<String>>() {}.getType());
        System.out.println(aClass);
    }
}