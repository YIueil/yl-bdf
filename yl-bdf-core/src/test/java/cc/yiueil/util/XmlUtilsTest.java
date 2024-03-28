package cc.yiueil.util;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.jaxen.JaxenException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

@Slf4j
class XmlUtilsTest {
    static Document document = null;

    @BeforeAll
    public static void before() {
        InputStream inputStream = XmlUtilsTest.class.getResourceAsStream("/dynamicsql/database.xml");
        try {
            document = XmlUtils.parse(inputStream);
        } catch (DocumentException e) {
            log.error(e.getMessage(), e);
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
        Element root = document.getRootElement();
        List<Node> nodes = document.selectNodes("//config");
        System.out.println();
    }

    @Test
    void selectNodes() {
        List<Node> nodes = XmlUtils.selectNodes(document, "//yl:config[@id='config1']", "yl");
        System.out.println();
    }

    @Test
    void selectSingleNode() {
        XmlUtils.selectSingleNode(document, "//yl:config", "yl").ifPresent(node -> {
            if (node instanceof Element) {
                Element element = (Element) node;
                System.out.println(element.element("sql").getText());
                List<Element> params = element.elements("params");
                System.out.println(params);
                List<Element> filters = element.elements("filters");
                System.out.println(filters);
                System.out.println(element.element("endSql").getText());
            }
        });

    }
}