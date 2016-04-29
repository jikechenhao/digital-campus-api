package com.hwedu.bookcross.service.impl;

import com.hwedu.bookcross.dao.IBookDAO;
import com.hwedu.bookcross.dao.IGroupDetailDAO;
import com.hwedu.bookcross.dto.GroupDetailDTO;
import com.hwedu.bookcross.po.GroupDetailPO;
import com.hwedu.bookcross.service.IGroupDetailService;
import com.hwedu.bookcross.vo.GroupDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lizhiqiang on 2016/4/6.
 */
@Service
@Transactional
public class GroupDetailService implements IGroupDetailService {

    @Autowired
    private IGroupDetailDAO groupDetailDAO;

    @Autowired
    private IBookDAO bookDAO;

    @Override
    public int appendBookInitTotal(String groupDetailId, Integer increment) {
        return groupDetailDAO.updateInitTotalByIdAndIncrement(groupDetailId, increment);
    }

    @Override
    public int refreshTotal(String groupId, String bookId, Integer errorTotal) {
        return groupDetailDAO.updateErrorAndCrossTotal(groupId, bookId, errorTotal);
    }

    @Override
    public List<GroupDetailVO> queryListBooksByGroupId(String groupId) {
        return groupDetailDAO.selectListByGroupId(groupId);
    }

    @Override
    public int save(GroupDetailDTO groupDetailDTO) {
        GroupDetailPO groupDetailPO = new GroupDetailPO();
        groupDetailPO.setBookId(groupDetailDTO.getBookId());
        groupDetailPO.setGroupId(groupDetailDTO.getGroupId());
        groupDetailPO.setInitTotal(groupDetailDTO.getInitTotal());
        return groupDetailDAO.insertOne(groupDetailPO);
    }

    @Override
    public boolean checkGroupDetailExists(GroupDetailDTO groupDetailDTO) {
        return false;
    }

    @Override
    public int removeGroupDetailByGroupIdAndBookId(String groupId, String bookId) {
        GroupDetailVO groupDetailVO = groupDetailDAO.selectOneByByGroupIdAndBookId(groupId, bookId);
        int i = bookDAO.updateStoreTotalById(groupDetailVO.getBookId(), groupDetailVO.getCrossTotal());
        int j = groupDetailDAO.deleteByGroupIdAndBookId(groupId, bookId);
        return i + j;
    }

    @Override
    public int adjustBookInGroup(String groupId, String bookId, Integer increment) {
        int i = bookDAO.updateStoreTotalById(bookId, 0 - increment);
        GroupDetailVO groupDetailVO = groupDetailDAO.selectOneByByGroupIdAndBookId(groupId, bookId);
        if (groupDetailVO == null) {
            i = i + groupDetailDAO.insertOne(makePO(groupId, bookId, increment));
        } else {
            i = i + groupDetailDAO.updateInitTotalByIdAndIncrement(groupDetailVO.getId(), increment);
        }
        return i;
    }

    @Override
    public int removeGroupDetailByBookId(String bookId) {
        return groupDetailDAO.deleteByBookId(bookId);
    }

    private GroupDetailPO makePO(String groupId, String bookId, Integer increment) {
        GroupDetailPO groupDetailPO = new GroupDetailPO();
        groupDetailPO.setBookId(bookId);
        groupDetailPO.setGroupId(groupId);
        groupDetailPO.setInitTotal(increment);
        groupDetailPO.setCrossTotal(increment);
        return groupDetailPO;
    }
}
