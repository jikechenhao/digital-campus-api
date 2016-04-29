package com.hwedu.bookcross.service.impl;

import com.hwedu.bookcross.common.parameter.Variables;
import com.hwedu.bookcross.dao.IProcessDAO;
import com.hwedu.bookcross.dto.ProcessDTO;
import com.hwedu.bookcross.po.ProcessPO;
import com.hwedu.bookcross.service.IProcessService;
import com.hwedu.bookcross.vo.ProcessVO;
import com.hwedu.bookcross.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lizhiqiang on 2016/4/6.
 */
@Service
@Transactional
public class ProcessService implements IProcessService {

    @Autowired
    private IProcessDAO processDAO;

    @Override
    public int saveProcess(ProcessDTO processDTO) {
        return processDAO.insert(dtoToPo(processDTO));
    }

    @Override
    public boolean checkStateNotBegin(String id) {
        return processDAO.selectOneById(id).getEnumState() < Variables.getInstance().getProcessStateRun();
    }

    @Override
    public int batchToggleState(List<String> json, Boolean isPause) {
        int i = 0;
        if (isPause) {
            for (String id : json) {
                i = i + processDAO.updateStateById(id, Variables.getInstance().getProcessStatePause());
            }
        } else {
            for (String id : json) {
                i = i + processDAO.updateStateById(id, Variables.getInstance().getProcessStateRun());
            }
        }
        return i;
    }

    @Override
    public int removeProcess(String id) {
        return processDAO.deleteById(id);
    }

    @Override
    public int changeWeekDayById(String id, Integer weekDay) {
        return processDAO.updateWeekDayById(id, weekDay);
    }

    @Override
    public int modifyProcessWhenNotRun(ProcessDTO processDTO) {
        return processDAO.update(processDTO);
    }

    @Override
    public int closeProcess(String processId) {
        return processDAO.updateStateById(processId, Variables.getInstance().getProcessStateEnd());
    }

    @Override
    public List<ProcessVO> queryListActivedProcess(String termId, Integer dayOfWeek, Integer... states) {
        return processDAO.selectListByTermIdAndDayOfWeekAndStates(termId, dayOfWeek, states);
    }

    @Override
    public List<StudentVO> testFindStudentsByClassId(String classId) {
        //缓存实现
        return null;
    }

    private ProcessPO dtoToPo(ProcessDTO dto) {
        ProcessPO po = new ProcessPO();
        po.setConfigId(dto.getConfigId());
        po.setEnumProcessState(dto.getEnumState());
        po.setExpectedSteps(dto.getExpectedSteps());
        po.setTermId(dto.getTermId());
        po.setGradeId(dto.getGradeId());
        po.setGroupIdJSON(dto.getGroupIdJSON());
        po.setName(dto.getName());
        po.setStartWeekDay(dto.getStartWeekDay());
        return po;
    }
}

