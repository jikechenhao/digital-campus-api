package com.hwedu.bookcross.service.impl;

import com.alibaba.fastjson.JSON;
import com.hwedu.bookcross.dao.IBookDAO;
import com.hwedu.bookcross.dao.IGroupDAO;
import com.hwedu.bookcross.dao.IGroupDetailDAO;
import com.hwedu.bookcross.dto.BookDTO;
import com.hwedu.bookcross.dto.GroupDetailDTO;
import com.hwedu.bookcross.po.BookPO;
import com.hwedu.bookcross.po.GroupDetailPO;
import com.hwedu.bookcross.po.GroupPO;
import com.hwedu.bookcross.service.IExcelService;
import com.hwedu.bookcross.vo.BookVO;
import com.hwedu.bookcross.vo.GroupDetailVO;
import com.hwedu.bookcross.vo.GroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lizhiqiang on 2016/4/25.
 */
@Service
@Transactional
public class ExcelService implements IExcelService {
    @Autowired
    private IBookDAO bookDAO;

    @Autowired
    private IGroupDAO groupDAO;

    @Autowired
    private IGroupDetailDAO groupDetailDAO;


    @Override
    public int exportBooksFromJSON(String bookJSON) {
        List<BookDTO> bookDTOs = JSON.parseArray(bookJSON, BookDTO.class);
        final int[] result = {0};
        bookDTOs.forEach(bookDTO -> {
            BookVO bookVO = bookDAO.selectOneByName(bookDTO.getName());
            if (bookVO != null) {
                result[0] += bookDAO.updateStoreTotalById(bookVO.getId(), bookDTO.getStoreTotal());
            } else {
                result[0] += bookDAO.insertOne(parseBookDTOToPO(bookDTO));
            }
        });
        return result[0];
    }

    @Override
    public int exportGroupDetailsFromJSON(String groupDetailJSON) {
        List<GroupDetailDTO> groupDetailDTOs = JSON.parseArray(groupDetailJSON, GroupDetailDTO.class);
        int result = 0;
        for (GroupDetailDTO groupDetailDTO : groupDetailDTOs) {
            //检查是否有这本书
            BookVO bookVO = bookDAO.selectOneByName(groupDetailDTO.getBookName());
            //如果没有则新增
            if (bookVO == null) {
                BookPO po = new BookPO();
                po.setName(groupDetailDTO.getBookName());
                po.setStoreTotal(groupDetailDTO.getInitTotal());
                result = result + bookDAO.insertOne(po);
                bookVO = bookDAO.selectOneById(po.getId());
            }

            //如果余数不够打断循环
            if (bookVO.getStoreTotal() - groupDetailDTO.getInitTotal() < 0) {
                return -1;
            } else {
                //开始扣除库存
                result = result + bookDAO.updateStoreTotalById(bookVO.getId(), 0 - groupDetailDTO.getInitTotal());
            }


            //检查是否已经有套系
            GroupVO groupVO = groupDAO.selectOneByName(groupDetailDTO.getGroupName());

            //如果没有，新建
            if (groupVO == null) {
                GroupPO groupPO = new GroupPO();
                groupPO.setName(groupDetailDTO.getGroupName());
                result = result + groupDAO.insertOne(groupPO);
                groupVO = groupDAO.selectOneByName(groupDetailDTO.getGroupName());
            }

            //检查是否有套系对应关系

            GroupDetailVO groupDetailVO = groupDetailDAO.selectOneByByGroupIdAndBookId(groupVO.getId(), bookVO.getId());

            //没有则新增
            if (groupDetailVO == null) {
                GroupDetailPO groupDetailPO = new GroupDetailPO();
                groupDetailPO.setGroupId(groupVO.getId());
                groupDetailPO.setBookId(bookVO.getId());
                groupDetailPO.setInitTotal(groupDetailDTO.getInitTotal());
                groupDetailPO.setErrorTotal(0);
                groupDetailPO.setCrossTotal(groupDetailDTO.getInitTotal());
                result = result + groupDetailDAO.insertOne(groupDetailPO);
            } else {
                //已经有则追加初始化量和流转量
                result = result + groupDetailDAO.updateInitTotalByIdAndIncrement(groupDetailVO.getId(), groupDetailDTO.getInitTotal());
            }
        }

        return result;
    }

    private BookPO parseBookDTOToPO(BookDTO bookDTO) {
        BookPO bookPO = new BookPO();
        bookPO.setName(bookDTO.getName());
        bookPO.setStoreTotal(bookDTO.getStoreTotal());
        return bookPO;
    }
}
