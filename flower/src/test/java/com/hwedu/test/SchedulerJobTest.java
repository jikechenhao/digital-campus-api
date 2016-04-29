package com.hwedu.test;

import com.hwedu.task.FlowerTaskHandler;
import com.hwedu.task.bean.SchedulerJobBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.quartz.Trigger.TriggerState;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.List;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@WebAppConfiguration()
public class SchedulerJobTest {
    //测试
    @Resource
    FlowerTaskHandler task;
    // 非业务
    @Resource
    private WebApplicationContext wac;
    private MockMvc mkm;

    @Before
    public void init() {
        mkm = MockMvcBuilders.webAppContextSetup(wac).build();
        try {
            task.loadPlanJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void updateCronExpressionTest() {
        try {
            List<SchedulerJobBean> jobBeans = FlowerTaskHandler.getJobBeans();
            SchedulerJobBean jobBean = jobBeans.get(0);
            System.out.println(jobBean.getCronExpression());//更新表达式前
            task.updateCronExpression(jobBean, "10 0/1 * * * ?");
            System.out.println(jobBean.getCronExpression());//更新表达式后
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    /**
     * deleteJob方法执行后未删除成功对应的job
     */
    @Test
    public void pauseAndResumeJob() {
        try {
            List<SchedulerJobBean> jobBeans = FlowerTaskHandler.getJobBeans();
            SchedulerJobBean jobBean = jobBeans.get(0);//获取clear前的jobBean
            TriggerState state = jobBean.getState();
            System.out.println(state.name());//停止job前状态
            task.pauseJob(jobBean);
            SchedulerJobBean jobBeanNew = jobBeans.get(0);//获取clear后的jobBean
            TriggerState stateNew = jobBeanNew.getState();
            System.out.println(stateNew.name());//停止job后状态
            task.resumeJob(jobBeanNew);
            SchedulerJobBean jobBeanNew2 = jobBeans.get(0);//获取clear后的jobBean
            TriggerState stateNew2 = jobBeanNew2.getState();
            System.out.println(stateNew2.name());//继续job后状态
            task.deleteJob(jobBeanNew2);
            SchedulerJobBean jobBeanNew3 = jobBeans.get(0);//获取clear后的jobBean
            TriggerState stateNew3 = jobBeanNew3.getState();
            System.out.println(stateNew3.name() + ",job=" + jobBeanNew3.getJobKey().getName());//继续job后状态
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    // 测试controller
    @Test
    public void test() throws Exception {
        mkm.perform(MockMvcRequestBuilders.get("/m/cross/book/2/6239642a-fd37-11e5-9c37-a36a6e094944")).andDo(MockMvcResultHandlers.print()).andReturn();
    }
}
