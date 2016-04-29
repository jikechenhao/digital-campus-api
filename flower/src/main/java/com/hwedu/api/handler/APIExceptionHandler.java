package com.hwedu.api.handler;

import com.hwedu.api.result.ErrorResult;
import com.hwedu.constants.Constants;
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
 * 异常分为两类：
 * 可预测的异常：如传入的参数没有通过业务逻辑检查，试图查询的结果并不存在，等等
 * 不可预测的异常：运行时异常等意外情况
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
    @ExceptionHandler(ErrorResult.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object handleAPIException(ErrorResult e) {
        logger.error("在java类 " + e.getClassT() + " 中出现异常，异常编码 " + e.getCode() + " 异常详细信息： ");
        logger.error(e.getMessage());
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
        return wrapExceptionResult(Constants.RESULT_CODE_ERROR_SYSTEM, "服务器内部错误！请联系管理员！");
    }

    /**
     * 封装错误响应对象，屏蔽掉多余的提示信息
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
