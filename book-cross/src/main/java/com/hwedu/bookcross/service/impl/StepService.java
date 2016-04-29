package com.hwedu.bookcross.service.impl;

import com.hwedu.bookcross.common.parameter.Variables;
import com.hwedu.bookcross.dao.IStepDAO;
import com.hwedu.bookcross.dto.StepDTO;
import com.hwedu.bookcross.po.StepPO;
import com.hwedu.bookcross.service.IStepService;
import com.hwedu.bookcross.vo.StepVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lizhiqiang on 2016/4/6.
 */
@Service
@Transactional
public class StepService implements IStepService {
    @Autowired
    private IStepDAO stepDAO;

    @Override
    public List<StepVO> queryListReadySteps(Integer readyState) {
        return stepDAO.selectListByState(readyState);
    }

    @Override
    public int modifyStepState(String stepId, Integer state) {
        return stepDAO.updateStateById(stepId, state);
    }

    @Override
    public StepVO queryLastStepByClassId(String classId) {
        return stepDAO.selectOneByClassIdAndMaxState(classId, Variables.getInstance().getStepStateEndOk());
    }

    @Override
    public int batchSaveSteps(List<StepDTO> steps) {
        int n = 0;
        for (int i = 0; i < steps.size(); i++) {
            n = n + stepDAO.insertOne(makePOFromDTO(steps.get(i)));
        }
        return n;
    }

    @Override
    public List<StepVO> queryListLastStep(String processId) {
        return stepDAO.selectListByProcessIdAndMaxIndex(processId);
    }

    @Override
    public Boolean checkStepExistsByProcessId(String processId) {
        Integer count = stepDAO.countByProcessId(processId);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    private StepPO makePOFromDTO(StepDTO dto) {
        StepPO po = new StepPO();
        po.setProcessId(dto.getProcessId());
        po.setEnumState(dto.getEnumState());
        po.setClassId(dto.getClassId());
        po.setGroupId(dto.getGroupId());
        po.setIndex(dto.getIndex());
        return po;
    }
}
