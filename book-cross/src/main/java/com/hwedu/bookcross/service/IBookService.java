package com.hwedu.bookcross.service;

import com.hwedu.bookcross.dto.BookDTO;
import com.hwedu.bookcross.vo.BookVO;

import java.util.List;

/**
 * 图书service接口
 * <p>
 * Created by lizhiqiang on 2016/3/30.
 */
public interface IBookService {
    /**
     * 新增图书
     * <p>
     * Created by lizhiqiang
     */
    int saveBook(BookDTO po);

    /**
     * 检查是否有同名，返回布尔值，如果有则把id存到dto中，用原指针返回
     * <p>
     * Created by lizhiqiang
     */
    boolean checkNameExists(BookDTO dto);

    /**
     * 根据id更新库存
     * <p>
     * Created by lizhiqiang
     */
    int modifyBookStoreById(String id, Integer increment);

    /**
     * 根据名称更新库存
     * <p>
     * Created by lizhiqiang
     */
//    int modifyBookStoreByName(String name, Integer increment);

    /**
     * 根据id查找
     * <p>
     * Created by lizhiqiang
     */
    BookVO queryOneBookById(String bookId);

    /**
     * 根据名称查找
     * <p>
     * Created by lizhiqiang
     */
    BookVO queryOneBookByName(String bookName);

    /**
     * 更新书籍信息，这里如果更新库存不是追加，而是覆盖
     * <p>
     * Created by lizhiqiang
     */
    int modifyBook(BookDTO bookDTO);

    int removeBookById(String bookId);

    boolean tryCutStore(String id, Integer increment);

    List<BookVO> queryAllBook();
}
