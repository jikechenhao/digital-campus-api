package com.hwedu.bookcross.service;

/**
 * Created by lizhiqiang on 2016/4/25.
 */
public interface IExcelService {
    int exportBooksFromJSON(String bookJSON);

    int exportGroupDetailsFromJSON(String groupDetailJSON);
}
