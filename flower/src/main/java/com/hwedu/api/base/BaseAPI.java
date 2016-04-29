package com.hwedu.api.base;

import com.hwedu.api.result.ErrorResult;
import com.hwedu.api.result.NormalResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * API层基类，封装一些公用的对象和方法
 * 该类不要过度封装，如果api层不希望被代码侵入的话，功能应该可以自己简单地实现，比如不继承基类，那么就需要声明一个logger
 * <p>
 * Created by lizhiqiang on 2016/4/8.
 */
public class BaseAPI {
    /**
     * 日志
     * <p>
     * Created by lizhiqiang
     */
    protected Logger logger = LogManager.getLogger();

    /**
     * 返回正常结果方法
     * <p>
     * Created by lizhiqiang
     */
    protected NormalResult makeNormalResult(Integer code, String message, Object obj) {
        return new NormalResult(code, message, obj);
    }

    /**
     * 返回异常结果方法
     * <p>
     * Created by lizhiqiang
     */
    protected ErrorResult makeErrorResult(Integer code, String message, Class<?> classT) {
        return new ErrorResult(code, message, classT);
    }


}
