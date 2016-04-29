package com.hwedu.bookcross.service;

import com.hwedu.bookcross.dto.StepDTO;
import com.hwedu.bookcross.vo.StepVO;

import java.util.List;

/**
 * Created by lizhiqiang on 2016/4/6.
 */
public interface IStepService {
    Boolean checkStepExistsByProcessId(String processId);

    List<StepVO> queryListLastStep(String processId);

    int batchSaveSteps(List<StepDTO> steps);

    List<StepVO> queryListReadySteps(Integer readyState);

    int modifyStepState(String stepId, Integer state);

    StepVO queryLastStepByClassId(String classId);

}
