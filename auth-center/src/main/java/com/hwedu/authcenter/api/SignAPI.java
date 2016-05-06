package com.hwedu.authcenter.api;

import com.alibaba.fastjson.JSON;
import com.hwedu.authcenter.api.base.BaseAPI;
import com.hwedu.authcenter.dto.SignDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录api
 * <p>
 * Created by lizhiqiang
 */
@RestController
@ResponseBody
@RequestMapping(value = "/a/sign")
public class SignAPI extends BaseAPI {
    @RequestMapping(value = "/0")
    public Object findValidBill(SignDTO signDTO) {
        logger.debug(JSON.toJSONString(signDTO));
        return normalResult("登录成功");
    }

    @RequestMapping(value = "/test")
    public Object test() {
        return normalResult("测试成功");
    }
}
