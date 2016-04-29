package com.hwedu.bookcross.dao;

import com.hwedu.bookcross.dto.GroupDTO;
import com.hwedu.bookcross.po.GroupPO;
import com.hwedu.bookcross.vo.GroupVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 套系DAO
 * <p>
 * Created by lizhiqiang on 2016/4/15.
 */
@Repository
public interface IGroupDAO {
    /**
     * 根据套系的名字返回套系信息
     */
    GroupVO selectOneByName(@Param("name") String name);

    /**
     * 新增一条记录
     */
    int insertOne(GroupPO groupPO);

    /**
     * 根据id检查套系可用状态
     * <p>
     * Created by lizhiqiang
     */
    boolean checkIsEnabled(@Param("groupId") String groupId);

    int updateOne(GroupDTO dto);

    int deleteById(@Param("id") String id);

    List<GroupVO> selectListIsEnabled(@Param("isEnabled") Boolean isEnabled);

}
