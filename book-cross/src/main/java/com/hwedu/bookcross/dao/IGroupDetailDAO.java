package com.hwedu.bookcross.dao;

import com.hwedu.bookcross.po.GroupDetailPO;
import com.hwedu.bookcross.vo.GroupDetailVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGroupDetailDAO {
    /**
     * 新增一条记录
     * <p>
     * Created by lizhiqiang
     */
    int insertOne(GroupDetailPO groupDetailPO);

    /**
     * 更新错误数和有效流转数
     * <p>
     * Created by lizhiqiang
     */
    int updateErrorAndCrossTotal(@Param("groupId") String groupId, @Param("bookId") String bookId,
                                 @Param("errorTotal") Integer errorTotal);

    /**
     * 查找某套系下的所有对应关系
     * <p>
     * Created by lizhiqiang
     */
    List<GroupDetailVO> selectListByGroupId(@Param("groupId") String groupId);

    /**
     * 在原有的初始量上进行增减
     * <p>
     * Created by lizhiqiang
     */
    int updateInitTotalByIdAndIncrement(@Param("id") String id, @Param("increment") Integer increment);

    /**
     * 移除某套系与某书的对应关系
     * <p>
     * Created by lizhiqiang
     */
    int deleteByGroupIdAndBookId(@Param("groupId") String groupId, @Param("bookId") String bookId);

    /**
     * 根据套系id删除相关的所有对应关系
     * <p>
     * Created by lizhiqiang
     */
    int deleteByGroupId(@Param("groupId") String groupId);

    /**
     * 根据书id删除所有对应关系
     * <p>
     * Created by lizhiqiang
     */
    int deleteByBookId(@Param("bookId") String bookId);

    /**
     * 根据套系id和书id返回对应关系vo
     * <p>
     * Created by lizhiqiang
     */
    GroupDetailVO selectOneByByGroupIdAndBookId(@Param("groupId") String groupId, @Param("bookId") String bookId);

}
