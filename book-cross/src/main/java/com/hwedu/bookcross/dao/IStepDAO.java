package com.hwedu.bookcross.dao;

import com.hwedu.bookcross.po.StepPO;
import com.hwedu.bookcross.vo.StepVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 轮转DAO
 * Created by lizhiqiang on 2016/4/15.
 */
@Repository
public interface IStepDAO {

    /**
     * 根据流程id查询轮转记录总数
     * <p>
     * Created by lizhiqiang
     */
    int countByProcessId(@Param("processId") String processId);

    /**
     * 根据流程id和最大步序数查询轮转信息列表
     * <p>
     * Created by lizhiqiang
     */
    List<StepVO> selectListByProcessIdAndMaxIndex(@Param("processId") String processId);

    /**
     * 根据id更新轮转记录的状态
     * <p>
     * Created by lizhiqiang
     */
    int updateStateById(@Param("stepId") String stepId, @Param("state") Integer state);

    /**
     * 新增一条记录·
     * <p>
     * Created by lizhiqiang
     */
    int insertOne(StepPO stepPO);

    /**
     * 查询处于某个状态值的所有轮转记录
     * <p>
     * Created by lizhiqiang
     */
    List<StepVO> selectListByState(@Param("state") Integer state);

    StepVO selectOneByClassIdAndMaxState(@Param("classId") String classId, @Param("state") int state);

}
