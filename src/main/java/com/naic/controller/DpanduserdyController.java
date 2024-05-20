package com.naic.controller;

import com.naic.common.Result;
import com.naic.entity.Dpuserdy;
import com.naic.service.DpanduserdyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dy")
public class DpanduserdyController {
    @Resource
    private DpanduserdyService dpanduserdyService;


    //    ================超级管理员==========================
    // 根据管理员名字查询对应
    @GetMapping("/selbyuser")
    public Result<?> selbyuser(@RequestParam String u){
        return Result.success(dpanduserdyService.selbyuser(u));
    }
    // 根据店铺名称查询对应管理员
    @GetMapping("/seluserbydpmc")
    public Result<?> seluserbydpmc(@RequestParam String d){
        return Result.success(dpanduserdyService.seluserbydpmc(d));
    }
    //查询店铺和管理员对应表的全部
    @GetMapping("/sal")
    public Result<?> seldpdyall(@RequestParam String u){
        return Result.success(dpanduserdyService.selall(u));
    }

    //    新增店铺对应
    @PostMapping("/addstoredy")
    public Result<?> addstoredy(@RequestBody Dpuserdy dpuserdy){
        return Result.success(dpanduserdyService.addstoredy(dpuserdy));
    }
    //    根据id删除店铺对应
    @DeleteMapping("/delstoredy")
    public Result<?> delstoredy(@RequestParam String i){
        return Result.success(dpanduserdyService.delstoredy(i));
    }
    //    修改店铺对应信息
    @PostMapping("/upstoredy")
    public Result<?> upstoredy(@RequestBody  Dpuserdy dpuserdy){
        return Result.success(dpanduserdyService.upstoredy(dpuserdy));
    }
}
