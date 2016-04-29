package com.hwedu.bookcross.service.impl;

import com.hwedu.bookcross.common.parameter.Variables;
import com.hwedu.bookcross.dao.IBookDAO;
import com.hwedu.bookcross.dao.IStepDAO;
import com.hwedu.bookcross.dao.IStepDetailDAO;
import com.hwedu.bookcross.po.BookPO;
import com.hwedu.bookcross.po.StepDetailPO;
import com.hwedu.bookcross.service.IStepDetailService;
import com.hwedu.bookcross.vo.BookVO;
import com.hwedu.bookcross.vo.GroupDetailVO;
import com.hwedu.bookcross.vo.StepDetailVO;
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
public class StepDetailService implements IStepDetailService {

    @Autowired
    private IBookDAO bookDAO;
    @Autowired
    private IStepDAO stepDAO;

    @Autowired
    private IStepDetailDAO stepDetailDAO;


    @Override
    public List<GroupDetailVO> countErrorByRoamId(String id) {
        return null;
    }

    @Override
    public List<StepDetailVO> queryNotBack(String classId) {
        return stepDetailDAO.selectListByClassIdAndState(classId, Variables.getInstance().getBookStateBackNotyet());
    }

    @Override
    public int backAnotherBook(String stepDetailId, String bookName) {
        BookVO bookVO = bookDAO.selectOneByName(bookName);
        int result = 0;
        if (bookVO != null) {
            result = result + bookDAO.updateStoreTotalById(bookVO.getId(), 1);
        } else {
            BookPO po = new BookPO();
            po.setName(bookName);
            po.setStoreTotal(1);
            result = result + bookDAO.insertOne(po);
        }
        result = result + stepDetailDAO.updateStateByIdAndOffset(stepDetailId, 2);
        return result;
    }

    @Override
    public int changeState(String stepDetailId, Integer state) {
        return stepDetailDAO.updateStateById(stepDetailId, state);
    }

    @Override
    public List<StepDetailVO> findBillNow(String classId) {
        StepVO stepVO = stepDAO.selectOneByClassIdAndMaxState(classId, Variables.getInstance().getStepStateEndOk());
        return stepDetailDAO.selectListByStepId(stepVO.getId());
    }

    @Override
    public int saveBatchBill(List<StepDetailPO> studentBookPOs) {
        int i = 0;
        for (StepDetailPO studentBookPO : studentBookPOs) {
            i = i + stepDetailDAO.insertOne(studentBookPO);
        }
        return i;
    }
}

