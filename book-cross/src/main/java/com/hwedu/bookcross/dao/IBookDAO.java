package com.hwedu.bookcross.dao;

import com.hwedu.bookcross.dto.BookDTO;
import com.hwedu.bookcross.po.BookPO;
import com.hwedu.bookcross.vo.BookVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 图书DAO
 * <p>
 * Created by lizhiqiang on 2016/3/30.
 */
@Repository
public interface IBookDAO {
    /**
     * 新增一条记录
     * <p>
     * Created by lizhiqiang
     */
    int insertOne(BookPO bookPO);

    /**
     * 根据图书名字查找图书信息
     * <p>
     * Created by lizhiqiang
     */
    BookVO selectOneByName(@Param("name") String name);

    /**
     * 根据图书id查找图书信息
     * <p>
     * Created by lizhiqiang
     */
    BookVO selectOneById(@Param("id") String id);

    /**
     * 更新图书信息
     */
    int updateOne(BookDTO dto);

    /**
     * 根据id删除图书信息，假删除
     */
    int deleteById(@Param("id") String id);

    /**
     * 根据书名更新库存
     */
    //int updateStoreTotalByName(@Param("name") String name, @Param("increment") Integer increment);

    /**
     * 根据id更新库存
     * <p>
     * Created by lizhiqiang
     */
    int updateStoreTotalById(@Param("id") String id, @Param("increment") Integer increment);

    /**
     * Created by lizhiqiang
     */
    int checkCutStoreTotalById(@Param("id") String id, @Param("increment") Integer increment);

    List<BookVO> selectAllBook();
}
