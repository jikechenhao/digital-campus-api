package com.hwedu.bookcross.service;

import com.hwedu.bookcross.po.StepDetailPO;
import com.hwedu.bookcross.vo.GroupDetailVO;
import com.hwedu.bookcross.vo.StepDetailVO;

import java.util.List;

/**
 * Created by lizhiqiang on 2016/4/6.
 */
public interface IStepDetailService {
    int saveBatchBill(List<StepDetailPO> studentBookPOs);

    List<StepDetailVO> findBillNow(String classId);

    int changeState(String stepDetailId, Integer state);

    int backAnotherBook(String stepDetailId, String bookName);

    List<StepDetailVO> queryNotBack(String classId);

    List<GroupDetailVO> countErrorByRoamId(String id);
}
