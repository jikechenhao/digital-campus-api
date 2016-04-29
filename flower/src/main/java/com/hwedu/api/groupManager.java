package com.hwedu.api;

import com.hwedu.api.base.BaseAPI;
import com.hwedu.api.result.ErrorResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/m/f/assess")
public class groupManager extends BaseAPI {


    @RequestMapping(value = "/c/0/{groupId}", method = RequestMethod.POST)
    public Object saveNewBook() throws ErrorResult {
        return null;
    }


}
