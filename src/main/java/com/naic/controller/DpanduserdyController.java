package com.naic.controller;

import com.naic.common.Result;
import com.naic.service.DpanduserdyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dy")
public class DpanduserdyController {
    @Resource
    private DpanduserdyService dpanduserdyService;



    //    ================超级管理员==========================
    //查询店铺和管理员对应表的全部
    @GetMapping("/sal")
    public Result<?> seldpdyall(@RequestParam String u){
        return Result.success(dpanduserdyService.selall(u));
    }
}
