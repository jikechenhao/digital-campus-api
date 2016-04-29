package com.hwedu.bookcross.service.impl;

import com.hwedu.bookcross.dao.IGroupDAO;
import com.hwedu.bookcross.dao.IGroupDetailDAO;
import com.hwedu.bookcross.dto.GroupDTO;
import com.hwedu.bookcross.po.GroupPO;
import com.hwedu.bookcross.service.IGroupService;
import com.hwedu.bookcross.vo.GroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lizhiqiang on 2016/4/6.
 */
@Service
@Transactional
public class GroupService implements IGroupService {
    @Autowired
    private IGroupDAO groupDAO;
    @Autowired
    private IGroupDetailDAO groupDetailDAO;

    @Override
    public boolean checkSameNameIsExists(GroupDTO dto) {
        GroupVO vo = groupDAO.selectOneByName(dto.getName());
        if (vo != null) {
            dto.setId(vo.getId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkGroupIsEnabled(String groupId) {
        return groupDAO.checkIsEnabled(groupId);
    }

    @Override
    public List<GroupVO> queryAllEnabled() {
        return groupDAO.selectListIsEnabled(true);
    }

    @Override
    public int removeGroup(String groupId) {
        int i = groupDetailDAO.deleteByGroupId(groupId);
        int j = groupDAO.deleteById(groupId);
        return i + j;
    }

    @Override
    public int modifyGroup(GroupDTO dto) {
        return groupDAO.updateOne(dto);
    }

    @Override
    public int saveGroup(GroupDTO groupDTO) {
        GroupPO groupPO = new GroupPO();
        groupPO.setName(groupDTO.getName());
        int i = groupDAO.insertOne(groupPO);
        groupDTO.setId(groupPO.getId());
        return i;
    }

/*	*//**
     * 查询所有可用套系
     *//*
    public PageBean<GroupVO> queryAllGroup(Integer currentPage, Integer pageSize) {
		PageBean<GroupVO> pb = new PageBean<GroupVO>();
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		List<GroupVO> list = groupDAO.queryAllGroup(pb);
		// 排序
		Collections.sort(list, new Comparator<GroupVO>() {
			@Override
			public int compare(GroupVO o1, GroupVO o2) {
				Long res = StringUtil.oneTwoThreeTo123(o1.getName()) - StringUtil.oneTwoThreeTo123(o2.getName());
				return res.intValue();
			}
		});
		pb.setList(list);
		return pb;
	}

	@Override
	public void deleteAndUpdateById(GroupDTO groupDTO) {
		groupDAO.deleteAndUpdateById(groupDTO);
	}

	@Override
	public GroupVO queryGroupIncludeBookList(String groupId) {
		GroupDTO gd = new GroupDTO();
		gd.setId(groupId);
		GroupVO gv = groupDAO.queryGroupIncludeBookList(gd);
		return gv;
	}*/
}
