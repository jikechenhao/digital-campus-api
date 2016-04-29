package com.hwedu.bookcross.common.tool;

import com.alibaba.fastjson.JSON;
import com.hwedu.bookcross.common.parameter.Variables;
import com.hwedu.bookcross.common.util.StringUtil;
import com.hwedu.bookcross.function.IDataToMapFunction;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * excel文件工具类
 * 从上传的文件当中解析数据
 * 定义模板并提供下载
 * <p>
 * Created by lizhiqiang on 2016/3/31.
 */
public final class ExcelTool {


    /**
     * 私有化构造方法
     */
    private ExcelTool() {

    }

    public static String pipeDataToJSONFromExcelByPOI(InputStream in, IDataToMapFunction func) {
        Workbook workbook;
        try {
            workbook = WorkbookFactory.create(in);
            int numberOfSheets = workbook.getNumberOfSheets();
            if (numberOfSheets == 0) {
                workbook.close();
            } else {
                List<Map<String, Object>> jsons = sortOutDataFromSheet(workbook.getSheetAt(0), func);
                workbook.close();
                return jsons == null ? "" : JSON.toJSONString(jsons);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Map<String, Object>> sortOutDataFromSheet(Sheet sheet, IDataToMapFunction pipeFunction) {
        int lastRowNum = sheet.getLastRowNum();//取有效行数
        if (lastRowNum < 2) {
            return null;
        } else {
            Row row = sheet.getRow(0);
            short lastCellNum = row.getLastCellNum();//取有效单元格数
            if (lastCellNum < 1) {
                return null;
            } else {
                List<Map<String, Object>> jsons = new ArrayList<>();
                for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
                    jsons.add(pipeFunction.dataToMap(sheet.getRow(rowIndex)));
                }
                return jsons;
            }
        }
    }

    /**
     * 根据单元格类型不同处理单元格的值，无论何种类型，都转为字符串类型返回
     *
     * @param cell
     * @return
     */
    public static String getCellStringValue(Cell cell) {
        String cellValue;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING://字符串类型
                cellValue = cell.getStringCellValue();
                if (StringUtil.isEmpty(cellValue)) {
                    cellValue = Variables.getInstance().getExcelBlankStringTag();
                }
                break;
            case Cell.CELL_TYPE_NUMERIC: //数值类型
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_BLANK:
                cellValue = Variables.getInstance().getExcelBlankStringTag();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                cellValue = Variables.getInstance().getExcelErrorTag();
                break;
            default:
                cellValue = Variables.getInstance().getExcelUndifinedTag();
                break;
        }
        return cellValue;
    }
}
