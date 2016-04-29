package com.hwedu.task;

import com.hwedu.common.tool.RedisTool;
import com.hwedu.task.bean.SchedulerJobBean;
import org.quartz.*;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 定时任务调度器 调用loadJobKeys方法获取当前运行中的所有jobkey
 * 然后调用启动、停止、恢复、更新任务表达式等方法
 *
 * @author htcgg
 */
@Component
public class FlowerTaskHandler {

    /**
     * 服务器启动时先从缓存数据库中读取配置，如果配置不存在则调用loadPlanJobs方法来初始化集合并将相关属性存入数据库；
     * 系统运行中客户端调用pauseJob、deleteJob、resumeJob等方法时刷新该集合
     */
    private static List<SchedulerJobBean> jobBeans = new ArrayList<>();// 当前运行中的所有job
    /**
     * 特别注意SchedulerFactoryBean是一个工厂方法，依赖注入的时候是它生产的scheduler接口
     * 直接注入SchedulerFactoryBean失败，报下面的错
     * Error creating bean with name 'taskScheduler': Injection of resource dependencies failed; nested exception is org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'startQuertz' must be of type [org.springframework.scheduling.quartz.SchedulerFactoryBean], but was actually of type [org.quartz.impl.StdScheduler]
     * 看spring源码可以知道，其实spring得到的是一个工厂bean，得到的不是它本身，而是它负责创建的org.quartz.impl.StdScheduler对象            所以要使用Scheduler对象
     */
    @Resource(name = "scheduler")
    Scheduler scheduler;

    public static List<SchedulerJobBean> getJobBeans() {
        return jobBeans;
    }

    public static void setJobBeans(List<SchedulerJobBean> jobBeans) {
        FlowerTaskHandler.jobBeans = jobBeans;
    }

    /**
     * 加载计划的所有定时任务，并保存定时任务相关属性，属性包括jobkey、triggerKey、state等
     *
     * @throws SchedulerException
     */
    public void loadPlanJobs() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();//获取计划中的job分组,默认是DEFAULT
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);//获取计划中的jobkey，spring配置中的id=myJobDetail
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);//获取继承trigger的所有子触发器
            for (Trigger trigger : triggers) {
                wrappJobBeans(jobKey, trigger);
            }
        }
    }

    /**
     * 加载运行中的任务，并保存定时任务相关属性信息
     *
     * @throws SchedulerException
     */
    public void loadRunningJobs() throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();//获取运行中的job
        for (JobExecutionContext executingJob : executingJobs) {
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            wrappJobBeans(jobKey, trigger);
        }
    }

    /**
     * 封装计划或运行中的job相关属性
     * trigger各状态说明：
     * None：Trigger已经完成，且不会在执行，或者找不到该触发器，或者Trigger已经被删除 NORMAL:正常状态 PAUSED：暂停状态 COMPLETE：触发器完成，但是任务可能还正在执行中 BLOCKED：线程阻塞状态 ERROR：出现错误
     *
     * @param jobKey
     * @param triggerKey
     * @throws SchedulerException
     */
    private void wrappJobBeans(JobKey jobKey, Trigger trigger) throws SchedulerException {
        TriggerKey triggerKey = trigger.getKey();
        SchedulerJobBean jobBean = new SchedulerJobBean();
        jobBean.setTriggerKey(triggerKey);
        jobBean.setJobKey(jobKey);
        TriggerState state = scheduler.getTriggerState(triggerKey);//获取触发器的运行状态，
        jobBean.setState(state);
        if (trigger instanceof CronTrigger) {
            CronTrigger cronTrigger = (CronTrigger) trigger;
            String cronExpression = cronTrigger.getCronExpression();
            jobBean.setCronExpression(cronExpression);
        }
        jobBeans.clear();//先清空任务列表
        jobBeans.add(jobBean);
    }

    /**
     * 停止定时任务
     *
     * @throws SchedulerException
     */
    public void pauseJob(SchedulerJobBean jobBean) throws SchedulerException {
        scheduler.pauseJob(jobBean.getJobKey());
        loadPlanJobs();
    }

    /**
     * 恢复定时任务
     *
     * @throws SchedulerException
     */
    public void resumeJob(SchedulerJobBean jobBean) throws SchedulerException {
        scheduler.resumeJob(jobBean.getJobKey());
        loadPlanJobs();
    }

    /**
     * 删除一个定时任务
     *
     * @throws SchedulerException
     */
    public void deleteJob(SchedulerJobBean jobBean) throws SchedulerException {
        scheduler.deleteJob(jobBean.getJobKey());
        loadPlanJobs();
    }

    /**
     * 更新一个触发器的cron表达式，更新之后立即开始任务
     *
     * @throws SchedulerException
     */
    public void updateCronExpression(SchedulerJobBean jobBean, String cron) throws SchedulerException {
        TriggerKey tk = jobBean.getTriggerKey();
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(tk);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);// 表达式调度构建器
        trigger = trigger.getTriggerBuilder().withIdentity(tk)
                .withSchedule(scheduleBuilder).build();// 按新的cronExpression表达式重新构建trigger
        scheduler.rescheduleJob(tk, trigger);// 按新的trigger重新设置job并立即执行
        jobBean.setCronExpression(cron);
        RedisTool.setValue("cronConfig", cron);
    }

}
