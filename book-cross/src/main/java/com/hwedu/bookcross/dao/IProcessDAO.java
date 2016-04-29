package com.hwedu.bookcross.dao;

import com.hwedu.bookcross.dto.ProcessDTO;
import com.hwedu.bookcross.po.ProcessPO;
import com.hwedu.bookcross.vo.ProcessVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 漂流流程DAO
 * Created by lizhiqiang on 2016/4/15.
 */
@Repository
public interface IProcessDAO {

    /**
     * 根据
     * 学期id
     * 周几触发轮转
     * 状态值数组
     * 查询所有流程列表
     * <p>
     * Created by lizhiqiang
     */
    List<ProcessVO> selectListByTermIdAndDayOfWeekAndStates(@Param("termId") String termId,
                                                            @Param("dayOfWeek") Integer dayOfWeek,
                                                            @Param("states") Integer[] states);

    /**
     * 根据id更新流程状态
     * <p>
     * Created by lizhiqiang
     */
    int updateStateById(@Param("processId") String processId, @Param("state") Integer state);

    int insert(ProcessPO processPO);

    ProcessVO selectOneById(String id);

    int update(ProcessDTO processDTO);

    int updateWeekDayById(@Param("id") String id, @Param("weekDay") Integer weekDay);

    int deleteById(String id);
}
