package com.hwedu.bookcross.function;

import org.apache.poi.ss.usermodel.Row;

import java.util.Map;

/**
 * 用以定义excel中的数据如何与bean中的属性对应
 * <p>
 * Created by lizhiqiang on 2016/4/25.
 */
@FunctionalInterface
public interface IDataToMapFunction {
    Map<String, Object> dataToMap(Row row);
}
