package cn.yiueil.util;

import org.dom4j.*;
import org.jaxen.JaxenException;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.dom4j.Dom4jXPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XmlUtilsTest {
    static Document document = null;

    @BeforeAll
    public static void before() {
        InputStream inputStream = XmlUtilsTest.class.getResourceAsStream("/dynamicsql/database.xml");
        try {
            document = XmlUtils.parse(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parse() {
        System.out.println(document);
    }

    @Test
    void print() {
        XmlUtils.print(document);
    }

    /**
     * 测试：
     */
    @Test
    public void testGetNodes() throws JaxenException {
        List<Node> nodes = document.selectNodes("//config");
        System.out.println();
    }
}