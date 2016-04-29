package com.hwedu.boot;

import com.hwedu.common.tool.RedisTool;
import com.hwedu.task.FlowerTaskHandler;
import com.hwedu.task.bean.SchedulerJobBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统启动开始定时任务预处理
 */
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    @Resource
    FlowerTaskHandler task;
    private Logger logger = LogManager.getLogger();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            logger.info("定时任务预处理开始！");
            String cronExpression = RedisTool.getValue("cronConfig");
            task.loadPlanJobs();
            if (cronExpression != null) {
                List<SchedulerJobBean> jobBeans = FlowerTaskHandler.getJobBeans();
                if (jobBeans.size() > 0) {
                    task.updateCronExpression(jobBeans.get(0), cronExpression);
                }
            }
            logger.info("定时任务预处理完成！");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
