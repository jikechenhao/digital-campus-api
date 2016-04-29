package com.hwedu.bookcross.function;


/**
 * 导入方法
 * <p>
 * Created by lizhiqiang on 2016/4/25.
 */
@FunctionalInterface
public interface IBatchImportFunction {
    int batchImport(String dataJSONs);
}