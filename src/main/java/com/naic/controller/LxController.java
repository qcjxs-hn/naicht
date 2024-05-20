package com.naic.controller;

import com.naic.common.Result;
import com.naic.entity.Lx;
import com.naic.service.LxService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/l")
public class LxController {
    @Resource
    private LxService lxService;
    //联系客服
    @PostMapping("/mes")
    public Result<?> fsxx(@RequestBody Lx lx){
        return Result.success(lxService.fsxx(lx));
    }
    //获取对应联系
    @GetMapping("/gm")
    public Result<?> getmes(@RequestParam String u){
        return Result.success(lxService.getmes(u));
    }
    //===================================管理员==================================
    @PostMapping("/hf")
    public Result<?> kfhf(@RequestBody Lx lx){
        return Result.success(lxService.glyhfxx(lx));
    }
}
