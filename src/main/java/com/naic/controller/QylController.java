package com.naic.controller;

import com.naic.common.Result;
import com.naic.service.QylService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/q")
public class QylController {
    @Resource
    QylService qylService;

    //首页权益栏
    @GetMapping("/all")
    public Result<?> getqylall(){
        return Result.success(qylService.getqyl());
    }
    //我的页面资源栏

}
