package com.hwedu.bookcross.function;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * Created by lizhiqiang on 2016/4/26.
 */
public interface IDownloadFunciton {
    void pushFileToStream(ServletOutputStream outputStream) throws IOException;

}
