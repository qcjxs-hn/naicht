package com.naic.controller;

import com.naic.common.Result;
import com.naic.entity.Storeinfo;
import com.naic.service.Ncservice;
import com.naic.service.StoreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/st")
public class StoreController {

    @Resource
    private StoreService storeService;

    @Resource
    private Ncservice ncservice;

    //查询附近店铺
    @GetMapping("/cxfj")
    public Result<?> getfjdp(@RequestParam String dw) {
        return Result.success(storeService.getfjstore(dw));
    }

    //查询奶茶
    @GetMapping("/cxnc")
    public Result<?> getbendiannc(@RequestParam String ncid) {
        return Result.success(ncservice.selbyid(ncid));

    }

    //根据店铺名称查询店铺信息
    @GetMapping("/cxdp")
    public Result<?> getdpbyname(@RequestParam String d) {
        return Result.success(storeService.getdpbyname(d));
    }

//        ==========================后台================================
    //    新增奶茶
    @PostMapping("/addstore")
    public Result<?> addstore(@RequestBody Storeinfo storeinfo) {
        return Result.success(storeService.addstore(storeinfo));
    }
    //超级管理员全查店铺
    @GetMapping("/superselstoreall")
    public Result<?> selstoreall(@RequestParam String u){
        return Result.success(storeService.selstoreall(u));
    }
    //    修改奶茶信息
    @PostMapping("/upstore")
    public Result<?> upstore(@RequestBody Storeinfo storeinfo){
        return Result.success(storeService.upstore(storeinfo));
    }
    //    根据id删除对应奶茶
    @DeleteMapping("/delstore")
    public Result<?> delstore(@RequestParam String i){
        return Result.success(storeService.delstore(i));
    }
}
