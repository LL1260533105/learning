package utils;

import annotation.ExcelExport;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于 poi excel导出工具类
 * @Author: lijilin
 * @Date: 2021/5/21 17:10
 * @Version: 1.0
 */
public class ExcelExportUtil {

    public static <T> void exportExcel(Workbook workbook, List<T> result) throws Exception {
        exportExcel(workbook, 0, "sheet1", result);
    }

    public static <T> void exportExcel(Workbook workbook, int sheetNum, String sheetTitle, List<T> result) throws Exception {
        // 生成一个表格
        Sheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 产生表格标题行
        Row row = sheet.createRow(0);
        // 遍历集合数据，产生数据行
        if (CollectionUtils.isEmpty(result)) {
            return;
        }
        Field[] declaredFields = result.get(0).getClass().getDeclaredFields();
        int cellIndex1 = 0;
        List<Field> newFields = new ArrayList<>();
        // 添加表头，并记录注解字段
        for (Field f : declaredFields) {
            if (f.isAnnotationPresent(ExcelExport.class)) {
                ExcelExport annotation = f.getAnnotation(ExcelExport.class);
                String value = annotation.value();
                Cell cell = row.createCell((short) cellIndex1);
                HSSFRichTextString text = new HSSFRichTextString(value);
                cell.setCellValue(text.toString());
                cellIndex1++;
                newFields.add(f);
            }
        }

        // 遍历数据，产生数据行
        int index = 1;
        for (T t : result) {
            row = sheet.createRow(index);
            int cellIndex = 0;
            for (Field f : newFields) {
                Cell cell = row.createCell((short) cellIndex);
                f.setAccessible(true);
                cell.setCellValue(f.get(t).toString());
                cellIndex++;
            }
            index++;
        }
    }
}
