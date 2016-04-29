package com.hwedu.bookcross.boot;

import com.hwedu.bookcross.common.parameter.Variables;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import static org.apache.logging.log4j.core.util.Loader.getClassLoader;

/**
 * 配置文件处理器
 * <p>
 * Created by lizhiqiang on 2016/4/19.
 */
public final class PropertiesHandler {
    /**
     * 配置项类
     */
    private static Properties properties = null;

    /**
     * 定时器
     */
    private static Timer timer = new Timer();

    /**
     * 配置文件名
     */
    private static String fileName = "config-variables.properties";

    /**
     * 私有化构造方法
     * <p>
     * Created by lizhiqiang
     */
    private PropertiesHandler() {

    }

    /**
     * 唯一的公开方法，有两个用处，一是系统启动完成时由预处理器显式加载这个类，二是用于手动立即刷新配置
     * <p>
     * Created by lizhiqiang
     */
    public static void reload() {
        load();
        pushValuesToVariables();
        timerRun();
    }

    /**
     * 定时器运行方法，每隔一段时间就刷新配置，这个刷新时间也是在配置文件中指定
     * <p>
     * Created by lizhiqiang
     */
    private static void timerRun() {
        timer.cancel();//取消定时器，该操作会终止定时器的执行线程
        timer = new Timer();//重新初始化定时器
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                reload();

            }
        }, Long.valueOf(get("RELOAD_DELAY")));
    }

    /**
     * 加载配置文件
     * <p>
     * Created by lizhiqiang
     */
    private static void load() {
        try {
            InputStream inputStream = getClassLoader().getResourceAsStream(fileName);

            if (properties != null && properties.size() != 0) {
                properties.clear();
            } else {
                properties = new Properties();
            }
            properties.load(new InputStreamReader(inputStream, "UTF-8"));

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据键取值
     * <p>
     * Created by lizhiqiang
     */
    private static String get(String key) {
        return String.valueOf(properties.get(key));
    }

    /**
     * 通过反射将配置值注入到全局参数单例对象中
     * <p>
     * Created by lizhiqiang
     */
    private static void pushValuesToVariables() {
        Field[] fields = Variables.class.getDeclaredFields();//得到所有成员变量，不包括父类的

        Variables variables = Variables.getInstance();//得到全局参数单例对象

        for (Field field : fields) {
            String fieldName = field.getName();//字段名称
            Class<?> fieldType = field.getType();//字段类型

            if (!fieldName.equals("instance")) {
                try {
                    field.setAccessible(true);//暴力反射
                    if (fieldType.equals(int.class)) {
                        field.setInt(variables, Integer.valueOf(get(fieldName)));//如果是整数类型
                    } else if (fieldType.equals(boolean.class)) {
                        field.setBoolean(variables, Boolean.valueOf(get(fieldName)));//如果是布尔类型
                    } else if (fieldType.equals(double.class)) {
                        field.setDouble(variables, Double.valueOf(get(fieldName)));//如果是双浮点数类型
                    } else if (fieldType.equals(long.class)) {
                        field.setLong(variables, Long.valueOf(get(fieldName)));//如果是长整数类型
                    } else if (fieldType.equals(short.class)) {
                        field.setShort(variables, Short.valueOf(get(fieldName)));//如果是短整数类型
                    } else if (fieldType.equals(float.class)) {
                        field.setFloat(variables, Float.valueOf(get(fieldName)));//如果是单浮点数类型
                    } else {
                        field.set(variables, get(fieldName));//对象类型
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
