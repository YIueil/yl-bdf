package cc.yiueil.util;

import cc.yiueil.convert.impl.collection.ListConverterAbstract;
import cc.yiueil.lang.reflect.AbstractTypeReference;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ParseUtilsTest {

    @Test
    void getStringDefaultEmptyStr() {
    }

    @Test
    void getStringDefaultNull() {
    }

    @Test
    void getString() {
        String string = ParseUtils.getString(1, "默认值");
        System.out.println(string);
    }

    @Test
    void getList() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add("2");
        objects.add("3");
        ListConverterAbstract<String> converter = new ListConverterAbstract<>(new AbstractTypeReference<String>() {});
        List<String> result = converter.convert(objects, Collections.emptyList());
        System.out.println(result);
    }

    @Test
    void get() {
    }

    @Test
    void testGetString() {
    }

    @Test
    void getInteger() {
        double d = 3.1415926;
        System.out.println(ParseUtils.getInteger(d, 0));
    }

    @Test
    void getDouble() {
    }

    @Test
    void testGet() {
    }

    @Test
    void testGetString1() {
    }

    @Test
    void testGetInteger() {
    }

    @Test
    void getFloat() {
    }

    @Test
    void testGetDouble() {
    }

    @Test
    void getLong() {
    }

    @Test
    void getBoolean() {
        System.out.println(ParseUtils.getBoolean("true", null));
        System.out.println(ParseUtils.getBoolean("false", null));
        System.out.println(ParseUtils.getBoolean(1, null));
        System.out.println(ParseUtils.getBoolean(0, null));
        System.out.println(ParseUtils.getBoolean(-1, null));
        System.out.println(ParseUtils.getBoolean("1", null));
    }

    @Test
    void testGet1() {
    }

    @Test
    void testGetString2() {
    }

    @Test
    void testGetInteger1() {
    }

    @Test
    void testGetFloat() {
    }

    @Test
    void testGetDouble1() {
    }

    @Test
    void testGetLong() {
    }

    @Test
    void testGetBoolean() {
    }

    @Test
    void testGetList() {

    }
}