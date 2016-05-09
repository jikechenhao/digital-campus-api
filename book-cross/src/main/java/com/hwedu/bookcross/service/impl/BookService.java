package com.hwedu.bookcross.service.impl;

import com.hwedu.bookcross.dao.IBookDAO;
import com.hwedu.bookcross.dto.BookDTO;
import com.hwedu.bookcross.po.BookPO;
import com.hwedu.bookcross.service.IBookService;
import com.hwedu.bookcross.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 图书service实现
 * <p>
 * Created by lizhiqiang on 2016/3/30.
 */
@Service
@Transactional
public class BookService implements IBookService {

    @Autowired
    private IBookDAO bookDAO;

    @Override
    public int removeBookById(String bookId) {
        return bookDAO.deleteById(bookId);
    }

    @Override
    public int saveBook(BookDTO dto) {
        BookPO po = convertDtoToPo(dto);
        int i = bookDAO.insertOne(po);
        dto.setId(po.getId());
        return i;
    }


    @Override
    public boolean checkNameExists(BookDTO dto) {
        BookVO vo = bookDAO.selectOneByName(dto.getName());
        if (vo != null) {
            dto.setId(vo.getId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int modifyBook(BookDTO bookDTO) {
        return bookDAO.updateOne(bookDTO);
    }

    @Override
    public int modifyBookStoreById(String id, Integer increment) {
        return bookDAO.updateStoreTotalById(id, increment);
    }

/*    @Override
    public int modifyBookStoreByName(String name, Integer increment) {
        return bookDAO.updateStoreTotalById(name, increment);
    }*/

    @Override
    public boolean tryCutStore(String id, Integer increment) {
        int i = bookDAO.checkCutStoreTotalById(id, increment);
        return i > 0;
    }

    @Override
    public List<BookVO> queryAllBook() {
        return bookDAO.selectAllBook();
    }

    @Override
    public BookVO queryOneBookById(String bookId) {
        return bookDAO.selectOneById(bookId);
    }

    @Override
    public BookVO queryOneBookByName(String bookName) {
        return bookDAO.selectOneByName(bookName);
    }

    private BookPO convertDtoToPo(BookDTO dto) {
        BookPO po = new BookPO();
        po.setName(dto.getName());
        po.setStoreTotal(dto.getStoreTotal());
        po.setAuthor(dto.getAuthor());
        po.setPress(dto.getPress());
        po.setIntro(dto.getIntro());
        return po;
    }

}
