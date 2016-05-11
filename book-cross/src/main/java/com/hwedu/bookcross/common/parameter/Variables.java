package com.hwedu.bookcross.common.parameter;

/**
 * 全局参数类，饿汉单例模式
 * 该类中的变量的值不以初始化值为准，将会被配置文件中的值覆盖，保留值是作为默认值存在
 * <p>
 * Created by lizhiqiang on 2016/3/31.
 */
public final class Variables {
    private static Variables instance = new Variables();
    private String redisIp;
    private int redisPort;
    private String excelBlankStringTag;
    private String excelErrorTag;
    private String excelUndifinedTag;
    private String excelDataMap;
    private String processDefaultConfigId;
    private int processStateWait;//已创建周期，但处于等待状态，不在定时处理机制中
    private int processStateReady;//周期刚刚被加入自动处理机制
    private int processStateRun;//周期正在运行
    private int processStatePause;//周期正在暂停
    private int processStateEnd;//周期已经结束
    private int stepStateReady;//已创建轮转，老师还没有发书
    private int stepStateBegin;//老师已经发书，轮转开始
    private int stepStateOver;//老师收书，轮转结束
    private int stepStateEndOk;//定时器运行时，结束本次轮转，并且检查书籍没有异常
    private int stepStateEndError;//定时器运行时，结束本次轮转，并且检查书籍有异常
    private int stepStateEndNotOver;//定时器再次启动时，老师仍然没有收书，超时结束
    private int stepStateEndNotBegin;//定时器再次启动时，老师没有发书，即轮转并没有开始
    private int bookStateReady;//借书单已经生成，准备借出图书
    private int bookStateOut;//图书正常借出到学生手中
    private int bookStateBackOld;//正常归还，是原书
    private int bookStateBackNew;//正常归还，是新书
    private int bookStateBackTimeoutOld;//超时归还，是原书
    private int bookStateBackTimeoutNew;//超时归还，是新书
    private int bookStateBackNotyet;//尚未归还
    private int resultCodeNormal;//正常返回的结果代码
    private int resultCodeError;//正常返回的结果代码
    private int resultCodeErrorSystem;//正常返回的结果代码

    private String excelNameInRequest;

    private Variables() {
    }

    public static Variables getInstance() {
        return instance;
    }

    public String getExcelBlankStringTag() {
        return excelBlankStringTag;
    }

    public String getExcelErrorTag() {
        return excelErrorTag;
    }

    public String getExcelUndifinedTag() {
        return excelUndifinedTag;
    }

    public String getRedisIp() {
        return redisIp;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public int getProcessStateWait() {
        return processStateWait;
    }

    public int getProcessStateReady() {
        return processStateReady;
    }

    public int getProcessStateRun() {
        return processStateRun;
    }

    public int getProcessStatePause() {
        return processStatePause;
    }

    public int getProcessStateEnd() {
        return processStateEnd;
    }

    public int getStepStateReady() {
        return stepStateReady;
    }

    public int getStepStateBegin() {
        return stepStateBegin;
    }

    public int getStepStateOver() {
        return stepStateOver;
    }

    public int getStepStateEndOk() {
        return stepStateEndOk;
    }

    public int getStepStateEndError() {
        return stepStateEndError;
    }

    public int getStepStateEndNotOver() {
        return stepStateEndNotOver;
    }

    public int getStepStateEndNotBegin() {
        return stepStateEndNotBegin;
    }

    public int getBookStateReady() {
        return bookStateReady;
    }

    public int getBookStateOut() {
        return bookStateOut;
    }

    public int getBookStateBackOld() {
        return bookStateBackOld;
    }

    public int getBookStateBackNew() {
        return bookStateBackNew;
    }

    public int getBookStateBackTimeoutOld() {
        return bookStateBackTimeoutOld;
    }

    public int getBookStateBackTimeoutNew() {
        return bookStateBackTimeoutNew;
    }

    public int getBookStateBackNotyet() {
        return bookStateBackNotyet;
    }

    public int getResultCodeNormal() {
        return resultCodeNormal;
    }

    public int getResultCodeError() {
        return resultCodeError;
    }

    public int getResultCodeErrorSystem() {
        return resultCodeErrorSystem;
    }

    public String getExcelDataMap() {
        return excelDataMap;
    }

    public String getProcessDefaultConfigId() {
        return processDefaultConfigId;
    }

    public String getExcelNameInRequest() {
        return excelNameInRequest;
    }
}
