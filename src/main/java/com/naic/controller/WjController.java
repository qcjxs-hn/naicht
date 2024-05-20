package com.naic.controller;

import com.naic.common.Result;
import com.naic.entity.Qyl;
import com.naic.entity.Wj;
import com.naic.service.WjService;
import org.springframework.web.bind.annotation.*;

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

//    ================================后台========================================
//    超级管理员全查说明文件
    @GetMapping("/all")
    public Result<?> getall(@RequestParam String u){
        return Result.success(wjService.selall(u));
    }
    //    新增说明文件
    @PostMapping("/addwj")
    public Result<?> addwj(@RequestParam String u,@RequestBody Wj wj){return Result.success(wjService.addwj(u,wj));}
    //    修改说明文件信息
    @PostMapping("/upwj")
    public Result<?> upwj(@RequestBody  Wj wj){return Result.success(wjService.upwj(wj));}
    //    根据createid删除对应说明文件
    @DeleteMapping("/delwj")
    public Result<?> delwj(@RequestParam String i){return Result.success(wjService.delwj(i));}

}
