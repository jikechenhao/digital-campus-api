package com.hwedu.api.result;

/**
 * 正常返回结果类
 * Created by lizhiqiang on 2016/4/18.
 */
public class NormalResult implements IRestResult {
    private Integer code;
    private String message;
    private Object result;

    public NormalResult() {
    }

    public NormalResult(Integer code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
