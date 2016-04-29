package com.hwedu.bookcross.exception;

/**
 * Created by lizhiqiang on 2016/4/26.
 */
public class CheckException extends Exception {
    private Integer code;
    private String message;

    public CheckException() {
    }

    public CheckException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

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
}
