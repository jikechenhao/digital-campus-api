package com.hwedu.bookcross.common.tool;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 远端数据获取器
 * <p>
 * Created by lizhiqiang on 2016/4/11.
 */
public final class $ {
    public static String get(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        if (!url.startsWith("http://")) {
            url = "http://" + url;
        }
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity repEntity = response.getEntity();
            String content = EntityUtils.toString(repEntity);

            response.close();
            httpClient.close();

            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
