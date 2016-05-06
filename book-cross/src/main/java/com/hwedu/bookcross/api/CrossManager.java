package com.hwedu.bookcross.api;

import com.hwedu.bookcross.api.base.BaseAPI;
import com.hwedu.bookcross.service.IStepDetailService;
import com.hwedu.bookcross.service.IStepService;
import com.hwedu.bookcross.vo.StepDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 流转管理器，用于封装在流转过程中的操作
 * <p>
 * Created by lizhiqiang on 2016/4/18.
 */
@RestController
@RequestMapping(value = "/m/cross/bill")
public class CrossManager extends BaseAPI {

//   看过期借书单

    @Autowired
    private IStepService stepService;

    @Autowired
    private IStepDetailService stepDetailService;

    @RequestMapping(value = "/r/0")
    public Object findValidBill(String classId) {
        List<StepDetailVO> stepDetailVOs = stepDetailService.findBillNow(classId);
        return normalResult("查询当前借书单成功");
    }

    @RequestMapping(value = "/u/1")
    public Object changeState(@RequestParam("id") String stepDetailId, @RequestParam("state") Integer state) {
        int i = stepDetailService.changeState(stepDetailId, state);
        return normalResult("更改状态成功");
    }

    @RequestMapping(value = "/u/2")
    public Object backAnotherBook(@RequestParam("id") String stepDetailId, @RequestParam("bookName") String
            bookName) {
        stepDetailService.backAnotherBook(stepDetailId, bookName);
        return normalResult("归还新书成功");
    }

    @RequestMapping(value = "/r/1")
    public Object findNotBack(@RequestParam("id") String classId) {
        List<StepDetailVO> stepDetailVOs = stepDetailService.queryNotBack(classId);
        return normalResult("查询未还记录成功", stepDetailVOs);
    }

    //根据当前年级查询当前流程

    //根据当前流程，查询经历过的所有步，以生成漂流图

    //根据某一步，查询该步的详情

    //点击某一步，进入查看该步下的借书单

    //统计所有异常记录，并根据步和流程分组，便于统一处理归还

    public Object findProcessNow() {
        return null;
    }
}
