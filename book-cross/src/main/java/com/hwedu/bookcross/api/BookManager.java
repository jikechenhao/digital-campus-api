package com.hwedu.bookcross.api;

import com.hwedu.bookcross.api.base.BaseAPI;
import com.hwedu.bookcross.dto.BookDTO;
import com.hwedu.bookcross.exception.CheckException;
import com.hwedu.bookcross.service.IBookService;
import com.hwedu.bookcross.service.IGroupDetailService;
import com.hwedu.bookcross.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 图书管理
 * <p>
 * Created by lizhiqiang on 2016/4/8.
 */
@RestController
@RequestMapping(value = "/m/cross/book")
public class BookManager extends BaseAPI {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IGroupDetailService groupDetailService;


    /**
     * 新增书，此处不追加已有图书的数量，如果已经有同名书，应给出提示
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/c", method = RequestMethod.POST)
    public Object saveNewBook(BookDTO bookDTO) throws CheckException {
        if (!bookService.checkNameExists(bookDTO)) {
            bookService.saveBook(bookDTO);
            return normalResult("新增图书成功");
        } else {
            throw checkException("已有同名图书");
        }
    }

    /**
     * 根据id查找图书信息
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/r/0/{bookId}", method = RequestMethod.GET)
    public Object findBookById(@PathVariable(value = "bookId") String bookId) {
        BookVO vo = bookService.queryOneBookById(bookId);
        return normalResult("查询图书成功", vo);
    }

    /**
     * 根据name查找图书信息
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/r/1", method = RequestMethod.POST)
    public Object findBookByName(@RequestParam("name") String bookName) {
        BookVO vo = bookService.queryOneBookByName(bookName);
        return normalResult("查询图书成功", vo);
    }

    /**
     * 在原有库存量的基础上做增减操作
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/u/1", method = RequestMethod.POST)
    public Object updateBookStore(@RequestParam("id") String id, @RequestParam("increment") Integer increment) {
        bookService.modifyBookStoreById(id, increment);
        return normalResult("修改库存成功");
    }

    /**
     * 更新图书信息
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/u/0", method = RequestMethod.POST)
    public Object updateBookInfo(BookDTO bookDTO) {
        bookService.modifyBook(bookDTO);
        return normalResult("修改图书信息成功");
    }

    /**
     * 删除图书，前端需要做一次验证
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/d", method = RequestMethod.POST)
    public Object removeBook(@RequestParam("id") String bookId) {
        //先删除所有的套系与书对应关系
        int i = groupDetailService.removeGroupDetailByBookId(bookId);

        //然后删除图书本身的记录
        int j = bookService.removeBookById(bookId);
        return normalResult("删除成功", i + j);
    }


    //正在使用中的图书不能删除！！！！！
}
