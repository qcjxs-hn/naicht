package com.naic.controller;

import com.naic.common.Result;
import com.naic.entity.Yhzyl;
import com.naic.service.GnService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//功能栏控制层
@RestController
@RequestMapping("/g")
public class GnController {
    @Resource
    private GnService gnService;

//    查询功能栏所有
    @GetMapping("/all")
    public Result<?> getgnall(){
        return Result.success(gnService.getgn());
    }

// 查询我的资源栏所有
    @GetMapping("/zall")
    public Result<?> getzyall(){
        return Result.success(gnService.getzyl());
    }
}
