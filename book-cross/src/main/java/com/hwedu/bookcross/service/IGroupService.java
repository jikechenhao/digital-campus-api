package com.hwedu.bookcross.service;

import com.hwedu.bookcross.dto.GroupDTO;
import com.hwedu.bookcross.vo.GroupVO;

import java.util.List;


/**
 * Created by lizhiqiang on 2016/4/6.
 */
public interface IGroupService {
    boolean checkSameNameIsExists(GroupDTO groupDTO);

    int saveGroup(GroupDTO groupDTO);

    boolean checkGroupIsEnabled(String groupId);

    int modifyGroup(GroupDTO dto);

    int removeGroup(String groupId);

    List<GroupVO> queryAllEnabled();
}
