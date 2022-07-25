package cn.yiueil.util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

/**
 * Author:YIueil
 * Date:2022/7/25 14:06
 * Description: 基于dom4j的xml解析工具类
 */
public class XmlUtils {
    /**
     * dom解析xml
     * @param inputStream xml流
     * @return 解析结果document类
     * @throws DocumentException 文档解析异常
     */
    public static Document parse(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(inputStream);
    }

    public static void print(Document document) {
        Element root = document.getRootElement();
        System.out.println("根节点:" + root.getName());
        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
            Element element = it.next();
            System.out.println("子节点:" + element.getName());
        }
    }
}
