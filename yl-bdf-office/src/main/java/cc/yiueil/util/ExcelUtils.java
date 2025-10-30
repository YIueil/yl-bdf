package cc.yiueil.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    private static final String EXCEL2003L = ".xls"; // 2003 - 版本的excel
    private static final String EXCEL2007U = ".xlsx"; // 2007 + 版本的excel

    /**
     * 简单表格解析 直接将流中的Excel转换成二维数组
     * @param inputStream 输入流
     * @param fileName 文件名称
     * @return 二维数组, 通过坐标获取对应单元格的值
     * @throws Exception 转换异常
     */
    public static Object[][] parseExcel(InputStream inputStream, String fileName) throws Exception {
        // 返回数据
        Object[][] result = null;

        // 根据文件名，创建excel工作薄
        Workbook work = getWorkbook(inputStream, fileName);

        Sheet sheet;
        Row row;
        Cell cell;

        // 遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            result = new Object[sheet.getLastRowNum() + 1][];

            // 遍历当前sheet中的所有行
            for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {
                row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                Object[] objects = new Object[row.getLastCellNum()];
                // 遍历所有的列
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    objects[y] = getCellValue(cell);
                }
                result[j] = objects;
            }

        }
        inputStream.close();
        return result;
    }

    /**
     * 简单表格解析 将流中的Excel转换成List<Map>
     *
     * @param inputStream 输入流
     * @param fileName 文件名（判断excel版本）
     * @param mapping  字段名称映射
     * @param titleRowNum 标题所在行 通常在第一行 此时参数填 0, 填 null 则使用第一行作为标题行
     *
     * @return 转换为 List<Map<String, Object>> 对象
     * @throws Exception 转换异常
     */
    public static List<Map<String, Object>> parseExcel(InputStream inputStream, String fileName, Map<String, String> mapping, Integer titleRowNum) throws Exception {
        //noinspection DuplicatedCode
        List<Map<String, Object>> result = new ArrayList<>();

        Sheet sheet;
        Row row;
        Cell cell;

        // 根据文件名，创建excel工作薄
        Workbook work = getWorkbook(inputStream, fileName);

        if (titleRowNum == null) {
            titleRowNum = 0;
        }

        // 遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            String[] title = initTitle(sheet, titleRowNum);
            if (title == null) {
                continue;
            }

            // 遍历当前sheet中的所有行
            for (int j = titleRowNum + 1; j < sheet.getLastRowNum() + 1; j++) {
                row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                Map<String, Object> m = new LinkedHashMap<>();
                // 遍历所有的列
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum() && y < title.length; y++) {
                    cell = row.getCell(y);
                    String key = title[y];
                    m.put(mapping.get(key), getCellValue(cell));
                }
                if (!m.isEmpty()) {
                    result.add(m);
                }
            }

        }

        // 关闭流 返回读取结果
        inputStream.close();

        return result;
    }

    /**
     * 简单表格解析 无映射将流中的Excel转换成List
     *
     * @param inputStream       输入流
     * @param fileName 文件名（判断excel版本）
     * @return 转换为 List<Map<String, Object>> 对象
     * @throws Exception 转换异常
     */
    public static List<Map<String, Object>> parseExcel(InputStream inputStream, String fileName, Integer titleRowNum) throws Exception {
        //noinspection DuplicatedCode
        List<Map<String, Object>> result = new ArrayList<>();

        Sheet sheet;
        Row row;
        Cell cell;

        // 根据文件名，创建excel工作薄
        Workbook work = getWorkbook(inputStream, fileName);

        if (titleRowNum == null) {
            titleRowNum = 0;
        }

        // 遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            String[] title = initTitle(sheet, titleRowNum);
            if (title == null) {
                continue;
            }

            // 遍历当前sheet中的所有行
            for (int j = titleRowNum + 1; j < sheet.getLastRowNum() + 1; j++) {
                row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                Map<String, Object> m = new LinkedHashMap<>();
                // 遍历所有的列
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum() && y < title.length; y++) {
                    cell = row.getCell(y);
                    String key = title[y];
                    m.put(key, getCellValue(cell));
                }
                if (!m.isEmpty()) {
                    result.add(m);
                }
            }

        }
        inputStream.close();
        return result;
    }

    /**
     * @param sheet sheet表
     * @param titleRowNum 标题所在行
     * @return 标题映射
     */
    private static String[] initTitle(Sheet sheet, int titleRowNum) {
        Row row;
        String[] title;
        Cell cell;
        // 取第一行标题
        row = sheet.getRow(titleRowNum);
        if (row != null) {
            title = new String[row.getLastCellNum()];
            for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                cell = row.getCell(y);
                title[y] = (String) getCellValue(cell);
            }
        } else {
            return null;
        }
        return title;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param ins      文件输入流
     * @param fileName 文件名
     * @return 工作表示例
     * @throws IOException 转换过程发生的异常
     */
    private static Workbook getWorkbook(InputStream ins, String fileName) throws IOException {
        Workbook wb = null;
        // 截取获得文件名后缀，用于判断版本信息
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (EXCEL2003L.equals(fileType)) {
            wb = new HSSFWorkbook(ins); // 2003
        } else if (EXCEL2007U.equals(fileType)) {
            wb = new XSSFWorkbook(ins); // 2007
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell 单元格
     * @return 单元格的值
     */
    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        Object value = null;
        DecimalFormat df = new DecimalFormat("#.######"); // 格式化number String字符
        DecimalFormat df2 = new DecimalFormat("#.######"); // 格式化数字
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyy-MM"); // 日期格式化

        CellType cellType = cell.getCellType();
        if (cellType == CellType.STRING) {
            value = cell.getRichStringCellValue().getString();
        } else if (cellType == CellType.NUMERIC) {
            if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                value = df.format(cell.getNumericCellValue());
            } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                value = sdf.format(cell.getDateCellValue());
            } else if ("m/d".equals(cell.getCellStyle().getDataFormatString())) {
                value = sdf1.format(cell.getDateCellValue());
            } else {
                value = df2.format(cell.getNumericCellValue());
            }
        } else if (cellType == CellType.BOOLEAN) {
            value = cell.getBooleanCellValue();
        } else if (cellType == CellType.BLANK) {
            value = "";
        }
        return value;
    }

}
