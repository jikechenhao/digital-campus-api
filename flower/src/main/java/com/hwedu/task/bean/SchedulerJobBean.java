package com.hwedu.task.bean;

import org.quartz.JobKey;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;

/**
 * 课堂评价定时任务调度实体类
 * 主要用于保存定时器运行过程中的参数
 *
 * @author htcgg
 */
public class SchedulerJobBean {

    private TriggerKey triggerKey; //定时触发器的key
    private JobKey jobKey; //定时任务key
    private TriggerState state;//trigger状态
    private String cronExpression;//定时任务表达式

    public TriggerKey getTriggerKey() {
        return triggerKey;
    }

    public void setTriggerKey(TriggerKey triggerKey) {
        this.triggerKey = triggerKey;
    }

    public JobKey getJobKey() {
        return jobKey;
    }

    public void setJobKey(JobKey jobKey) {
        this.jobKey = jobKey;
    }

    public TriggerState getState() {
        return state;
    }

    public void setState(TriggerState state) {
        this.state = state;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }


}
