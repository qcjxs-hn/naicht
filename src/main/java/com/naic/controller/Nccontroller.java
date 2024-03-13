package com.naic.controller;

import com.naic.common.Result;
import com.naic.entity.Ncdd;
import com.naic.entity.Ncpl;
import com.naic.mapper.Ncmapper;
import com.naic.mapper.Ncplmapper;
import com.naic.service.Ncddservice;
import com.naic.service.Ncggservice;
import com.naic.service.Ncservice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/nc")
public class Nccontroller {

    @Resource
    private Ncservice ncservice;

    @Resource
    private Ncggservice ncggservice;

    @Resource
    private Ncddservice ncddservice;
    //获取侧边栏
    @GetMapping("/cbl")
    public Result<?> getncplcbl(){
        return Result.success(ncservice.getncplcbl());
    }

    //获取奶茶规格
    @GetMapping("/gg")
    public Result<?> getncgg(@RequestParam String i){
        return Result.success(ncggservice.getncgg(i));
    }

    //创建奶茶订单
    @PostMapping("/cjdd")
    public Result<?> cjdd(@RequestBody Ncdd ncdd){
        return Result.success(ncddservice.cjdd(ncdd));
    }
    //确认奶茶订单支付
    @PostMapping("/qr")
    public Result<?> qrdd(@RequestParam String ic,@RequestParam String iu){
//        System.out.println(ic+":"+iu);
        return Result.success(ncddservice.qrdd(ic.replace(" ","+"),iu.replace(" ","+")));
    }
    //根据账号查询用户奶茶订单
    @GetMapping("/selncdd")
    public Result<?> selncddbyuser(@RequestParam String u){
        return Result.success(ncddservice.selddbyuser(u));
    }
//==========================后台===========================================
//    根据店铺查询相关订单
    @GetMapping("/seldd")
    public Result<?> selddbydpmc(@RequestParam String d){
        return Result.success(ncddservice.selddbydpmc(d));
    }
//       超级管理员全查
    @GetMapping("/superselall")
    public Result<?> selddbysupername(@RequestParam String u){
        return Result.success(ncddservice.selddbysupername(u));
    }
}
