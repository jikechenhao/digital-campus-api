package com.hwedu.bookcross.task;

import com.alibaba.fastjson.JSON;
import com.hwedu.bookcross.common.parameter.Variables;
import com.hwedu.bookcross.common.util.CalendarUtil;
import com.hwedu.bookcross.dto.StepDTO;
import com.hwedu.bookcross.po.StepDetailPO;
import com.hwedu.bookcross.service.IGroupDetailService;
import com.hwedu.bookcross.service.IProcessService;
import com.hwedu.bookcross.service.IStepDetailService;
import com.hwedu.bookcross.service.IStepService;
import com.hwedu.bookcross.vo.GroupDetailVO;
import com.hwedu.bookcross.vo.ProcessVO;
import com.hwedu.bookcross.vo.StepVO;
import com.hwedu.bookcross.vo.StudentVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhiqiang on 2016/4/7.
 * 图书漂流定时任务，分为如下步骤：
 * 检查是否有在活动状态的漂流周期
 * 如果有，检查目标周期内是否已经有轮转记录
 * 如果有，归档当前轮转并计算错误，开启下次轮转作为当前轮转
 * 如果没有，开启首次轮转作为当前轮转
 * 根据当前轮转生成借书单，结束定时任务
 * 如果没有周期记录，直接结束定时任务
 * <p>
 * 定时任务每天凌晨3点运行一次
 */
@Component
public class CrossTask {
    private Logger logger = LogManager.getLogger();
    private Jedis jedis = new Jedis(Variables.getInstance().getRedisIp(), Variables.getInstance().getRedisPort());

    @Autowired
    private IProcessService processService;
    @Autowired
    private IStepService stepService;
    @Autowired
    private IGroupDetailService groupDetailService;
    @Autowired
    private IStepDetailService stepDetailService;

    //注释掉定时任务注解，保留xml以方便修改配置，如果放开注解，在xml中相应地注释掉相关配置即可
    //@Scheduled(cron = "0 * * * * ?")
    public void cross() {

        logger.info("定时任务开始执行");

        String termId = pullTermId();//获取当前学期ID
        Integer dayOfWeek = CalendarUtil.dayOfWeek();//取当前是周几
        logger.debug("开始查询当前的活动周期，term:" + termId + " week:" + dayOfWeek);

        List<ProcessVO> processVOs = processService.queryListActivedProcess(termId, dayOfWeek, Variables.getInstance().getProcessStateReady(), Variables.getInstance().getProcessStateRun());//取所有的活动周期

        if (processVOs != null && processVOs.size() > 0) {//如果查到有活动周期的话，开始处理流程
            logger.info("查询到活动周期，开始处理流程");
            logger.info("开始处理轮转");
            startStep(processVOs);//处理轮转过程
            logger.info("正在生成新的借书单");
            makeNewBill();//生成当前轮转的新借书单

        } else {//没有活动周期则本次定时任务结束
            logger.info("没有查询到活动周期，本次定时任务将结束");
        }
        logger.info("定时任务执行结束");
    }

    /**
     * 处理轮转流程
     */
    private void startStep(List<ProcessVO> processVOs) {
        for (ProcessVO processVO : processVOs) {
            if (stepService.checkStepExistsByProcessId(processVO.getId())) {
                logger.info("已经有轮转记录，继续下一次轮转");
                startNextStep(processVO);
            } else {
                logger.info("没有轮转记录，开始新的轮转");
                startFirstStep(processVO);
            }
        }
    }

    /**
     * 没有轮转记录，开启第一步
     */
    private void startFirstStep(ProcessVO processVO) {
        //没有轮转记录则新开
        String groupJSON = processVO.getGroupIdJSON();
        //取书组的id集合
        List<String> groupIds = JSON.parseArray(groupJSON, String.class);
        List<String> classIds = JSON.parseArray(pullClassIdsByGradeId("d5289376-ff84-11e5-97bf-a77a14afffde"), String.class);
        //理论上套数应该等于班级总数
        if (groupIds.size() >= classIds.size()) {
            List<StepDTO> dtos = new ArrayList<>();
            //生成新的流转记录
            for (int i = 0; i < classIds.size(); i++) {
                StepDTO dto = new StepDTO();
                dto.setProcessId(processVO.getId());
                dto.setIndex(1);//第一步
                dto.setEnumState(Variables.getInstance().getStepStateReady());
                dto.setGroupId(groupIds.get(i));
                dto.setClassId(classIds.get(i));
                dtos.add(dto);
            }
            stepService.batchSaveSteps(dtos);
        } else {
            logger.warn(processVO.getName() + "配置的套数小于年级的班级总数！！！");
        }
    }

    /**
     * 已有轮转记录，前进一步
     */
    private void startNextStep(ProcessVO processVO) {
        List<StepVO> stepVOs = stepService.queryListLastStep(processVO.getId());          //取最后一次轮转
        int index = stepVOs.get(0).getIndex();                                      //取最后一次流转的序数
        handleRoamError(stepVOs);                                        //统计是否有异常书籍，如果有则更新流转数量
        if (index < processVO.getExpectedSteps()) {                  //如果最后一次流转的步数小于周期的预计最大步数，则认为可以向前一步
            startNextRoam(processVO.getId(), stepVOs, true);         //开始下一步轮转
        } else {//否则认为流转过程已经结束，关闭周期
            processService.closeProcess(processVO.getId());
        }
    }

    /**
     * 开始下一步轮转
     * 以班级序列为准，控制书组序列，可选顺时针和逆时针
     */
    private void startNextRoam(String configVOId, List<StepVO> stepVOs, Boolean isClockwise) {
        int newIndex = stepVOs.get(0).getIndex() + 1;
        List<StepDTO> dtos = new ArrayList<>();
        for (int i = 0; i < stepVOs.size(); i++) {
            StepDTO dto = new StepDTO();
            dto.setProcessId(configVOId);
            dto.setIndex(newIndex);
            dto.setEnumState(Variables.getInstance().getStepStateReady());

            String groupId;
            String classId;


            if (isClockwise) {                                              //以班级序列为基准，顺时针轮转书组
                if (i == 0) {                                                           //首位特殊处理
                    classId = stepVOs.get(0).getClassId();                               //首位的班级id
                    groupId = stepVOs.get(stepVOs.size() - 1).getGroupId();              //末位的书组id
                } else {                                                                //其他位的处理
                    classId = stepVOs.get(i).getClassId();                              //当前位的班级ID
                    groupId = stepVOs.get(i - 1).getGroupId();                            //上一位的书组ID
                }
            } else {                                                        //以班级序列为基准， 逆时针轮转书组
                if (i == stepVOs.size() - 1) {                                             //末位特殊处理
                    classId = stepVOs.get(i).getClassId();                               //末位的班级id
                    groupId = stepVOs.get(0).getGroupId();                              //首位的书组id
                } else {                                                                //其他位的处理
                    classId = stepVOs.get(i).getClassId();                              //当前位的班级ID
                    groupId = stepVOs.get(i + 1).getGroupId();                            //下一位的书组ID
                }
            }

            dto.setClassId(classId);
            dto.setGroupId(groupId);

            dtos.add(dto);
        }
        stepService.batchSaveSteps(dtos);
    }

    /**
     * 检查轮转过程中的异常，更新书籍的流转数量，改变流程的状态
     *
     * @param stepVOs
     */
    private void handleRoamError(List<StepVO> stepVOs) {
        for (StepVO stepVO : stepVOs) {
            if (stepVO.getEnumState().equals(Variables.getInstance().getStepStateReady())) {//如果轮转仍然未开始
                stepService.modifyStepState(stepVO.getId(), Variables.getInstance().getStepStateEndNotBegin());//更新状态
            } else if (stepVO.getEnumState().equals(Variables.getInstance().getStepStateBegin())) {//如果轮转仍然在运行状态，即老师没有手动结束
                stepService.modifyStepState(stepVO.getId(), Variables.getInstance().getStepStateEndNotOver());
                //更新状态
            } else if (stepVO.getEnumState().equals(Variables.getInstance().getStepStateOver())) {//轮转结束
                //返回本次轮转内所有异常书籍
                List<GroupDetailVO> studentBookVOs = stepDetailService.countErrorByRoamId(stepVO.getId());
                //根据书籍id和roam的groupid，更新书的流转数量
                if (studentBookVOs != null && studentBookVOs.size() > 0) {
                    for (GroupDetailVO bookGroupVO : studentBookVOs) {
                        groupDetailService.refreshTotal(bookGroupVO.getGroupId(), bookGroupVO.getBookId(), bookGroupVO.getErrorTotal());
                    }
                    //如果有异常书籍，更新状态
                    stepService.modifyStepState(stepVO.getId(), Variables.getInstance().getStepStateEndError());
                    //更新状态
                } else {
                    //如果没有异常书籍，更新状态
                    stepService.modifyStepState(stepVO.getId(), Variables.getInstance().getStepStateEndOk());//更新状态
                }
            } else {//理论上不能达到这里
                logger.error("检查最后一步轮转时检测到大于结束的状态值");
            }
        }
    }

    /**
     * 生成新的借书单
     */
    private void makeNewBill() {
        //获取当前所有有效轮转记录
        List<StepVO> stepVOs = stepService.queryListReadySteps(Variables.getInstance().getStepStateReady());
        int i = 1;
        for (StepVO stepVO : stepVOs) {
            List<StudentVO> studentVOs = pullStudentsByClassId(stepVO.getClassId());

            List<GroupDetailVO> groupDetailVOs = groupDetailService.queryListBooksByGroupId(stepVO.getGroupId());
            int total = 0;//当前书组中书的总数
            for (GroupDetailVO vo : groupDetailVOs) {
                total = total + vo.getCrossTotal();
            }
            int size = studentVOs.size();
            if (total >= studentVOs.size()) {//如果书足够给学生发
                List<StepDetailPO> stepDetailPOs = new ArrayList<>();
                makeBillItem(groupDetailVOs, studentVOs, stepVO.getId(), stepDetailPOs);
                stepDetailService.saveBatchBill(stepDetailPOs);
                logger.info("开始发放第" + i + "套书，总计" + total + "本，发给" + size + "名学生，实际发放" + stepDetailPOs.size() + "本");
            } else {
                logger.info("开始发放第" + i + "套书，总计" + total + "本，发给" + size + "名学生，不够了 " + stepVO.getGroupId());
                logger.info("书籍不够，将有学生拿不到书");
            }
            i++;
        }
    }

    private void makeBillItem(List<GroupDetailVO> bookGroupVOs, List<StudentVO> studentVOs, String roamId, List<StepDetailPO>
            studentBookPOs) {
        if (studentVOs.size() == 0) {//如果学生序列为空，跳出递归
            return;
        } else {
            if (bookGroupVOs.size() == 0) {//如果书序列为空，跳出递归
                return;
            } else {//如果书序列不为空
                Integer crossTotal = bookGroupVOs.get(0).getCrossTotal();
                if (crossTotal == 0) {//首位书的流转量等于0，移除首位书
                    bookGroupVOs.remove(0);
                } else {
                    StepDetailPO stepDetailPO = new StepDetailPO();
                    stepDetailPO.setBookId(bookGroupVOs.get(0).getBookId());//取首位书的id
                    stepDetailPO.setStudentId(studentVOs.get(0).getId());//首位学生id
                    stepDetailPO.setStepId(roamId);//轮转id
                    stepDetailPO.setEnumState(Variables.getInstance().getBookStateReady());
                    studentBookPOs.add(stepDetailPO);//加入序列
                    bookGroupVOs.get(0).setCrossTotal(crossTotal - 1);//首位书流转量减去1
                    studentVOs.remove(0);//移除首位学生
                }
                makeBillItem(bookGroupVOs, studentVOs, roamId, studentBookPOs);//尾递归
            }
        }
    }

    /**
     * 从远端获取当前学期id
     */
    private String pullTermId() {
        return jedis.get("term_id");
    }

    /**
     * 从远端获取年级下所有班级id
     *
     * @param gradeId
     * @return
     */
    private String pullClassIdsByGradeId(String gradeId) {
        return jedis.get(gradeId);
    }

    /**
     * 根据班级id，从远端获取学生信息
     */
    private List<StudentVO> pullStudentsByClassId(String classId) {
        return processService.testFindStudentsByClassId(classId);
    }


}
