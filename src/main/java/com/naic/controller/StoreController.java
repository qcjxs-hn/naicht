package com.naic.controller;

import com.naic.common.Result;
import com.naic.service.Ncservice;
import com.naic.service.StoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/st")
public class StoreController {

    @Resource
    private StoreService storeService;

    @Resource
    private Ncservice ncservice;
//查询附近店铺
    @GetMapping("/cxfj")
    public Result<?> getfjdp(@RequestParam String dw){
        return Result.success(storeService.getfjstore(dw));
    }
//查询奶茶
    @GetMapping("/cxnc")
    public Result<?> getbendiannc(@RequestParam String ncid){
        return Result.success(ncservice.selbyid(ncid));

    }
    //根据店铺名称查询店铺信息
    @GetMapping("/cxdp")
    public Result<?> getdpbyname(@RequestParam String d){
        return Result.success(storeService.getdpbyname(d));
    }
}
