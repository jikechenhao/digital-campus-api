package com.hwedu.bookcross.api;

import com.alibaba.fastjson.JSON;
import com.hwedu.bookcross.api.base.BaseAPI;
import com.hwedu.bookcross.common.parameter.Variables;
import com.hwedu.bookcross.common.tool.RemoteDataGetter;
import com.hwedu.bookcross.dto.ProcessDTO;
import com.hwedu.bookcross.exception.CheckException;
import com.hwedu.bookcross.service.IProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lizhiqiang on 2016/4/11.
 */
@RestController
@RequestMapping(value = "/m/cross/process")
public class ProcessManager extends BaseAPI {

    @Autowired
    private IProcessService processService;

    @RequestMapping(value = "/c")
    public Object addProcess(ProcessDTO processDTO) {
        processDTO.setTermId(RemoteDataGetter.getTermIdNow());
        processDTO.setExpectedSteps(RemoteDataGetter.getClassListByGradeId(processDTO.getGradeId()).size());
        processDTO.setEnumState(Variables.getInstance().getProcessStateReady());
        processDTO.setConfigId(Variables.getInstance().getProcessDefaultConfigId());
        processService.saveProcess(processDTO);
        return normalResult("新增漂流活动成功");
    }

    @RequestMapping(value = "/u/0")
    public Object modifyProcessWhenNotRun(ProcessDTO processDTO) throws CheckException {
        if (processService.checkStateNotBegin(processDTO.getId())) {
            throw checkException("漂流已经开始，不能更改信息");
        } else {
            processService.modifyProcessWhenNotRun(processDTO);
            return normalResult("修改漂流信息成功");
        }
    }

    @RequestMapping(value = "/u/1", method = RequestMethod.POST)
    public Object changeWeekDay(@RequestParam("id") String id, @RequestParam("weekDay") Integer weekDay) {
        processService.changeWeekDayById(id, weekDay);
        return normalResult("修改漂流日期成功");
    }

    @RequestMapping(value = "/u/2", method = RequestMethod.POST)
    public Object batchToggleState(@RequestParam("ids") String ids, @RequestParam("isPause") Boolean isPause) {
        List<String> json = JSON.parseArray(ids, String.class);
        processService.batchToggleState(json, isPause);
        return normalResult("切换漂流状态成功");
    }

    @RequestMapping(value = "/u/3", method = RequestMethod.POST)
    public Object closeProcess(@RequestParam("id") String id) {
        processService.closeProcess(id);
        return normalResult("关闭流程活动成功");
    }

    @RequestMapping(value = "/d", method = RequestMethod.POST)
    public Object removeProcess(@RequestParam("id") String id) {
        int i = processService.removeProcess(id);
        return normalResult("删除漂流活动成功");
    }
}
