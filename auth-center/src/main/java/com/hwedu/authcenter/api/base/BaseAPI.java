package com.hwedu.authcenter.api.base;

import com.hwedu.authcenter.exception.CheckException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * API层基类，封装一些公用的对象和方法
 * 该类不要过度封装，如果api层不希望被代码侵入的话，功能应该可以自己简单地实现，比如不继承基类，那么就需要声明一个logger
 * <p>
 * Created by lizhiqiang on 2016/4/8.
 */
public class BaseAPI {
    /**
     * 日志对象
     * <p>
     * Created by lizhiqiang
     */
    protected Logger logger = LogManager.getLogger();

    /**
     * 调用此方法，构造一个对象用于封装正常的返回结果，包括一个code，返回的信息，和可能会有的返回对象
     * <p>
     * Created by lizhiqiang
     */
    protected Map<String, Object> normalResult(String message, Object... objs) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", message);
        if (objs.length > 0) {
            result.put("result", objs[0]);
        }
        return result;
    }

    /**
     * 调用此方法，构造一个对象用于封装逻辑异常，包括一个code，返回的信息，要注意该错误只是逻辑有误，而不是真正的异常
     * 使用异常封装是为了让spring错误处理器能够捕获
     * 继承了exception的自定义异常将带有很多的父类信息，需要在spring错误处理器中修剪掉
     * <p>
     * Created by lizhiqiang
     */
    protected CheckException checkException(String message) {
        return new CheckException(500, message);
    }

}
