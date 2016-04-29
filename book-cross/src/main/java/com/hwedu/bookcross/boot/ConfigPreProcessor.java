package com.hwedu.bookcross.boot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 系统配置项预处理器
 * 该预处理器将在系统初始化完成后，调用配置文件处理器，初始化系统变量
 * <p>
 * Created by lizhiqiang on 2016/4/19.
 */
@Component
public class ConfigPreProcessor implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger = LogManager.getLogger();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("配置文件预处理器开始执行");
        PropertiesHandler.reload();
        logger.info("配置文件预处理器执行完毕");
    }
}
