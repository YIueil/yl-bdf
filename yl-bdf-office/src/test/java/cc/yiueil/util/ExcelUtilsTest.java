package cc.yiueil.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;

class ExcelUtilsTest {

    @Test
    void parseExcel() throws Exception {
        System.out.println("parseExcel");
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\-因子表.xlsx"));
        Object[][] objects = ExcelUtils.parseExcel(fileInputStream, "-因子表.xlsx");
        System.out.println(objects);
    }
}