package com.naic.controller;

import com.naic.common.Result;
import com.naic.service.WjService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/w")
public class WjController {
    @Resource
    private WjService wjService;

//    根据名字查询说明文件
    @GetMapping("/sel")
    public Result<?> getwjbyti(@RequestParam String ti){
        return Result.success(wjService.getwjbyti(ti));
    }
}
