package com.hwedu.bookcross.common.tool;

import org.springframework.core.convert.converter.Converter;

/**
 * @author xyc 空字符过滤转换器
 */
public class CustomerEmptyStringConvertor implements Converter<String, String> {
    @Override
    public String convert(String source) {
        if (null != source) {
            if ("".equals(source.trim())) {
                return null;
            }
        } else {
            return null;
        }
        return source;
    }

}
