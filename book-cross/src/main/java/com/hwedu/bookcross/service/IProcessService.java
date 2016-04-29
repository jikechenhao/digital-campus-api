package com.hwedu.bookcross.service;

import com.hwedu.bookcross.dto.ProcessDTO;
import com.hwedu.bookcross.vo.ProcessVO;
import com.hwedu.bookcross.vo.StudentVO;

import java.util.List;

/**
 * Created by lizhiqiang on 2016/4/6.
 */
public interface IProcessService {
    List<ProcessVO> queryListActivedProcess(String termId, Integer dayOfWeek, Integer... state);

    int closeProcess(String configId);

    List<StudentVO> testFindStudentsByClassId(String classId);

    int saveProcess(ProcessDTO processDTO);


    int modifyProcessWhenNotRun(ProcessDTO processDTO);

    boolean checkStateNotBegin(String id);

    int changeWeekDayById(String id, Integer weekDay);

    int batchToggleState(List<String> json, Boolean isPause);

    int removeProcess(String id);
}
