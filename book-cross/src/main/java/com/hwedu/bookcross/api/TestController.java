package com.hwedu.bookcross.api;

import com.alibaba.fastjson.JSON;
import com.hwedu.bookcross.api.base.BaseAPI;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 李志强 on 2016/5/10.
 */
@RestController
@RequestMapping(value = "/m/test")
public class TestController extends BaseAPI {
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object testGet() {
        System.out.println("-------------------------get---------------------");
        return normalResult("get success");
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Object testPost(HttpServletRequest request) {
        System.out.println("---------------------post------------------------");
        System.out.println("========="+JSON.toJSONString(request.getParameterMap()));
        return normalResult("post success");
    }
}
