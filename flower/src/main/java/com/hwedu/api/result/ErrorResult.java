package com.hwedu.api.result;


/**
 * 异常返回结果类
 * Created by lizhiqiang on 2016/4/18.
 */
public class ErrorResult extends Exception implements IRestResult {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;
    private Class<?> classT;//用于判定哪个java类报错

    public ErrorResult() {
    }

    public ErrorResult(Integer code, String message, Class<?> classT) {
        this.code = code;
        this.message = message;
        this.classT = classT;
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

    public Class<?> getClassT() {
        return classT;
    }

    public void setClassT(Class<?> classT) {
        this.classT = classT;
    }


}
