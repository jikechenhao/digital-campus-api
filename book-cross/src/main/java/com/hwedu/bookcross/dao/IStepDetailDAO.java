package com.hwedu.bookcross.dao;

import com.hwedu.bookcross.po.StepDetailPO;
import com.hwedu.bookcross.vo.StepDetailVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 轮转详情DAO
 * <p>
 * Created by lizhiqiang on 2016/4/15.
 */
@Repository
public interface IStepDetailDAO {

    /**
     * 新增轮转详情DAO
     * <p>
     * Created by lizhiqiang
     */
    int insertOne(StepDetailPO stepDetailPO);

    List<StepDetailVO> selectListByStepId(@Param("id") String id);

    int updateStateById(@Param("id") String stepDetailId, @Param("state") Integer state);

    int updateStateByIdAndOffset(@Param("id") String stepDetailId, @Param("offset") Integer offset);

    List<StepDetailVO> selectListByClassIdAndState(@Param("classId") String classId, @Param("state") int state);
}
