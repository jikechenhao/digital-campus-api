package com.hwedu.bookcross.api;

import com.hwedu.bookcross.api.base.BaseAPI;
import com.hwedu.bookcross.common.parameter.Variables;
import com.hwedu.bookcross.common.tool.ExcelTool;
import com.hwedu.bookcross.common.util.StringUtil;
import com.hwedu.bookcross.exception.CheckException;
import com.hwedu.bookcross.function.IBatchImportFunction;
import com.hwedu.bookcross.function.IDataToMapFunction;
import com.hwedu.bookcross.function.IDownloadFunciton;
import com.hwedu.bookcross.service.IExcelService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * excel相关操作处理类
 * <p>
 * Created by lizhiqiang on 2016/4/15.
 */
@Controller
@RequestMapping(value = "/m/cross/excel")
public class ExcelManager extends BaseAPI {

    @Autowired
    private IExcelService excelService;

    /**
     * 导入图书
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/book/upload")
    @ResponseBody
    public Object uploadBook(HttpServletRequest request) throws CheckException {
        return uploadHandle(request,
                (row) -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", ExcelTool.getCellStringValue(row.getCell(0)));
                    String s = ExcelTool.getCellStringValue(row.getCell(1));
                    Integer i = Double.valueOf(s).intValue();
                    map.put("storeTotal", i);
                    return map;
                },
                (datas) -> {
                    return excelService.exportBooksFromJSON(datas);
                }
        );
    }

    /**
     * 导入套系
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/group/upload")
    @ResponseBody
    public Object uploadGroup(HttpServletRequest request) throws CheckException {
        return uploadHandle(request,
                (row) -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("groupName", ExcelTool.getCellStringValue(row.getCell(0)));
                    map.put("bookName", ExcelTool.getCellStringValue(row.getCell(1)));
                    String s = ExcelTool.getCellStringValue(row.getCell(2));
                    Integer i = Double.valueOf(s).intValue();
                    map.put("initTotal", i);
                    return map;
                },
                (datas) -> {
                    return excelService.exportGroupDetailsFromJSON(datas);
                });
    }

    /**
     * 下载图书模板
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/book/download")
    public void downloadBookTemplete(HttpServletResponse response) throws CheckException {
        downloadHandle(response, "图书模板.xls", (outputStream) -> {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("sheet1");
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("图书名称");
            row.createCell(1).setCellValue("数量");
            wb.write(outputStream);
            outputStream.close();
            wb.close();
        });
    }

    @RequestMapping(value = "/group/download")
    public void downloadGroupTemplete(HttpServletResponse response) throws CheckException {
        downloadHandle(response, "套系模板.xls", (outputStream) -> {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("sheet1");
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("套系名称");
            row.createCell(1).setCellValue("图书名称");
            row.createCell(2).setCellValue("图书数量");
            wb.write(outputStream);
            outputStream.close();
            wb.close();
        });
    }

    /**
     * 下载模板公共处理
     * <p>
     * Created by lizhiqiang
     */
    private void downloadHandle(HttpServletResponse response, String fileName, IDownloadFunciton downloadFunciton) throws CheckException {
        try {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
            downloadFunciton.pushFileToStream(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw checkException(e.getMessage());
        }
    }

    /**
     * excel导入时的公共处理
     * <p>
     * Created by lizhiqiang
     */
    private Object uploadHandle(HttpServletRequest request, IDataToMapFunction pipeFunction, IBatchImportFunction importBatchFunction) throws CheckException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile(Variables.getInstance().getExcelNameInRequest());
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
                String jsons;
                try {
                    jsons = ExcelTool.pipeDataToJSONFromExcelByPOI(file.getInputStream(), pipeFunction);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                    throw checkException("获取文件流失败");
                }
                if (!StringUtil.isEmpty(jsons)) {
                    int i = importBatchFunction.batchImport(jsons);
                    if (i >= 0) {
                        return normalResult("上传成功，共导入" + i + "行");
                    } else {
                        throw checkException("上传失败，某本图书库存不够");
                    }
                } else {
                    throw checkException("未解析到有效内容");
                }
            } else {
                throw checkException("文件后缀名错误");
            }
        } else {
            throw checkException("没有获取到文件");
        }
    }
}
