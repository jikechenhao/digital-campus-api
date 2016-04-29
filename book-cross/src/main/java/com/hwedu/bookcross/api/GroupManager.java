package com.hwedu.bookcross.api;

import com.hwedu.bookcross.api.base.BaseAPI;
import com.hwedu.bookcross.dto.GroupDTO;
import com.hwedu.bookcross.exception.CheckException;
import com.hwedu.bookcross.service.IBookService;
import com.hwedu.bookcross.service.IGroupDetailService;
import com.hwedu.bookcross.service.IGroupService;
import com.hwedu.bookcross.vo.GroupDetailVO;
import com.hwedu.bookcross.vo.GroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/m/cross/group")
public class GroupManager extends BaseAPI {

    @Autowired
    private IBookService bookService;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IGroupDetailService groupDetailService;


    /**
     * 新增套系，此时是一个空套系，无对应关系
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/c", method = RequestMethod.POST)
    public Object saveGroup(GroupDTO dto) {
        groupService.saveGroup(dto);
        return normalResult("新增套系成功");

    }

    /**
     * 更新套系本身的信息，不包括对应关系
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/u/0", method = RequestMethod.POST)
    public Object updateGroupInfo(GroupDTO dto) {
        groupService.modifyGroup(dto);
        return normalResult("修改套系成功");
    }

    /**
     * 调整套系中图书的数量
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/u/1", method = RequestMethod.POST)
    public Object adjustBookInGroup(@RequestParam("groupId") String groupId, @RequestParam("bookId") String bookId, @RequestParam("increment") Integer increment) throws CheckException {
        //不支持修改正在使用中的套系
        if (groupService.checkGroupIsEnabled(groupId)) {
            if (increment > 0 && !bookService.tryCutStore(bookId, increment)) {
                throw checkException("库存图书不足");
            }
            groupDetailService.adjustBookInGroup(groupId, bookId, increment);
            return normalResult("操作成功");
        } else {
            throw checkException("套系正在使用中，不能修改");
        }
    }

    /**
     * 根据套系id和书id，从套系中移除对应关系
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/d/2/{bookId}/{groupId}", method = RequestMethod.GET)
    public Object removeGroupDetail(@PathVariable(value = "bookId") String bookId,
                                    @PathVariable(value = "groupId") String groupId) throws CheckException {
        //不支持修改正在使用中的套系
        if (groupService.checkGroupIsEnabled(groupId)) {
            groupDetailService.removeGroupDetailByGroupIdAndBookId(groupId, bookId);
            return normalResult("操作成功");
        } else {
            throw checkException("套系正在使用中，不能修改");
        }
    }

    /**
     * 根据套系查询相关图书信息
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/r/0/{groupId}", method = RequestMethod.GET)
    public Object findBooksByGroupId(@PathVariable(value = "groupId") String groupId) {
        List<GroupDetailVO> booksByGroupId = groupDetailService.queryListBooksByGroupId(groupId);
        return normalResult("查询成功", booksByGroupId);
    }

    /**
     * 删除套系，同时移除所有图书对应关系
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/d/{groupId}", method = RequestMethod.GET)
    public Object removeGroup(@PathVariable(value = "groupId") String groupId) {
        groupService.removeGroup(groupId);
        return normalResult("操作成功");
    }

    /**
     * 查询所有可用的套系
     * <p>
     * Created by lizhiqiang
     */
    @RequestMapping(value = "/r/1", method = RequestMethod.GET)
    public Object findAllEnabled() {
        List<GroupVO> vos = groupService.queryAllEnabled();
        return normalResult("操作成功", vos);
    }
}
