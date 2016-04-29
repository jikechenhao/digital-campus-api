package com.hwedu.bookcross.common.util;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by lizhiqiang on 2016/3/31.
 */
public final class StringUtil {
    /**
     * 私有化构造方法，使其无法实例化从而成为工具类
     */
    private StringUtil() {

    }

    /**
     * 验证字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if (str != null && !str.trim().isEmpty()) {// 如果字符串不为null，去除空格后值不与空字符串相等的话，证明字符串有实质性的内容
            return false;// 不为空
        }
        return true;// 为空
    }

    /**
     * 生成一个新的uuid字符串，去掉分隔符
     */
    public static String makeUUID32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成一个新的uuid字符串，保留分隔符
     */
    public static String makeUUID36() {
        return UUID.randomUUID().toString();
    }

    /**
     * 截取一个字符串的后缀名
     */
    public static String subSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
    }

    /**
     * 经中文数字转换为阿拉伯数字只用于排序
     *
     * @param str
     * @return
     */
    public static Long oneTwoThreeTo123(String str) {
        if (null == str) {
            return 0L;
        }
        HashMap<Character, Integer> hash = new HashMap<>();
        hash.put('零', 0);
        hash.put('一', 1);
        hash.put('二', 2);
        hash.put('三', 3);
        hash.put('四', 4);
        hash.put('五', 5);
        hash.put('六', 6);
        hash.put('七', 7);
        hash.put('八', 8);
        hash.put('九', 9);
        hash.put('十', 10);
        hash.put('百', 100);
        hash.put('千', 1000);
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            if (null != hash.get(charArray[i])) {
                sb.append(hash.get(charArray[i]));
            }
        }
        return sb.length() == 0 ? -1L : Long.valueOf(sb.toString());
    }
}
