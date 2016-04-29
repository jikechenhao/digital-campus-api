package com.hwedu.bookcross.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author htcgg
 *         用于读取*.properties配置文件
 */
public class ConfigUtil {

    /**
     * @param filePath 配置文件路径例如："exception.properties"
     * @param key      例如："code500"
     * @return 返回配置文件中key对应的value值
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String fetchResource(String filePath, String key) throws FileNotFoundException, IOException {
        Properties config = new Properties();
        InputStream in = Object.class.getResourceAsStream("/" + filePath);
        config.load(new InputStreamReader(in, "UTF-8"));
        return config.getProperty(key);
    }

}
