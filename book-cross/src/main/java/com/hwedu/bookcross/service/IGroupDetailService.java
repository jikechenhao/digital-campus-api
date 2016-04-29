package com.hwedu.bookcross.service;

import com.hwedu.bookcross.dto.GroupDetailDTO;
import com.hwedu.bookcross.vo.GroupDetailVO;

import java.util.List;

/**
 * Created by lizhiqiang on 2016/4/6.
 */
public interface IGroupDetailService {
    boolean checkGroupDetailExists(GroupDetailDTO groupDetailDTO);

    int appendBookInitTotal(String groupDetailId, Integer increment);

    int save(GroupDetailDTO groupDetailDTO);

    List<GroupDetailVO> queryListBooksByGroupId(String groupId);

    int refreshTotal(String groupId, String bookId, Integer errorTotal);

    int removeGroupDetailByGroupIdAndBookId(String groupId, String bookId);

    int removeGroupDetailByBookId(String bookId);

    int adjustBookInGroup(String groupId, String bookId, Integer increment);
}
