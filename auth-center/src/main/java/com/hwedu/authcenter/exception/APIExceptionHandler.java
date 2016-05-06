package com.hwedu.authcenter.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理
 * <p>
 * 理想情况下，无论程序运行结果如何，客户端一定会收到统一格式的回复，即使出错了也一样
 * <p>
 * 可以捕获的异常分为三种：
 * 业务逻辑异常，封装为自定义的exception子类
 * 编译时异常，即需要try/catch代码块中捕获的异常，这些异常是exception的子类，但不是RuntimeException的子类
 * 运行时异常，RuntimeException，这种异常不用try/catch，例如内存溢出等
 * <p>
 * <p>
 * Created by lizhiqiang on 2016/4/17.
 */
@ControllerAdvice
public class APIExceptionHandler {
    private Logger logger = LogManager.getLogger();

    /**
     * 业务逻辑异常处理
     * <p>
     * Created by lizhiqiang
     */
    @ExceptionHandler(CheckException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object handleCheckException(CheckException e) {
        logger.warn("业务逻辑异常：" + e.getMessage());
        return wrapExceptionResult(e.getCode(), e.getMessage());
    }

    /**
     * 运行时异常处理
     * <p>
     * Created by lizhiqiang
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object handleRuntimeException(RuntimeException e) {
        e.printStackTrace();//打印错误信息
        logger.error(e.getMessage());//记录日志
        return wrapExceptionResult(400, "运行时错误！请联系管理员！");
    }

    /**
     * 修剪异常信息，只返回异常编码和异常的提示信息，去除其他多余的属性
     * <p>
     * Created by lizhiqiang
     */
    private Map<String, Object> wrapExceptionResult(Integer code, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("message", message);
        return result;
    }
}
