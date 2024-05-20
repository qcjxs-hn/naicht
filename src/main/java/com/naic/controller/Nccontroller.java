package com.naic.controller;

import com.naic.common.Result;
import com.naic.entity.Ncdd;
import com.naic.entity.Ncgg;
import com.naic.entity.Ncpl;
import com.naic.entity.Ncpljl;
import com.naic.mapper.Ncmapper;
import com.naic.mapper.Ncplmapper;
import com.naic.service.Ncddservice;
import com.naic.service.Ncggservice;
import com.naic.service.Ncservice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    //发送取餐
    @PostMapping("/fsqc")
    public Result<?> fsqc(@RequestParam String i){return Result.success(ncddservice.fsqc(i));}
//    根据店铺查询相关订单
    @GetMapping("/seldd")
    public Result<?> selddbydpmc(@RequestParam String d){
        return Result.success(ncddservice.selddbydpmc(d));
    }
//       超级管理员奶茶订单全查
    @GetMapping("/superselall")
    public Result<?> selddbysupername(@RequestParam String u){
        return Result.success(ncddservice.selddbysupername(u));
    }
    //    根据id删除订单
    @DeleteMapping("/delncdd")
    public Result<?> delncdd(@RequestParam String i){
        return Result.success(ncddservice.delncdd(i));
    }
    //    根据id更新订单
    @PostMapping("/upncdd")
    public Result<?> updatancdd(@RequestBody Ncdd ncdd){
        return Result.success(ncddservice.updatancdd(ncdd));
    }
//       超级管理员奶茶规格全查
    @GetMapping("/superselncggall")
    public Result<?> selncggddbysupername(@RequestParam String u){
        return Result.success(ncggservice.selggbysupername(u));
    }
    //    根据id删除规格
    @DeleteMapping("/delncgg")
    public Result<?> delncgg(@RequestParam String i){
        return Result.success(ncggservice.delncgg(i));
    }
    //    根据id更新规格
    @PostMapping("/upncgg")
    public Result<?> updatancgg(@RequestBody Ncgg ncgg){
        return Result.success(ncggservice.updatancgg(ncgg));
    }
    //  超级管理员添加规格
    @PostMapping("/addncgg")
    public Result<?> addncgg(@RequestParam String u, @RequestBody Ncgg ncgg){
        return Result.success(ncggservice.addncgg(u,ncgg));
    }
    //    新增侧边栏
    @PostMapping("/addncplcbl")
    public Result<?> addncplcbl(@RequestBody Ncpl ncpl){
        return Result.success(ncservice.addncplcbl(ncpl));
    }
    //    修改侧边栏
    @PostMapping("/upncplcbl")
    public Result<?> upncplcbl(@RequestBody Ncpl ncpl){
        return Result.success(ncservice.upncplcbl(ncpl));
    }
    //    根据id删除对应侧边栏
    @DeleteMapping("/delncplcbl")
    public Result<?> delncplcbl(@RequestParam String i){
        return Result.success(ncservice.delncplcbl(i));
    }
    //   超级管理员奶茶全查
    @GetMapping("/superselncall")
    public Result<?> selncall(@RequestParam String u){
        return Result.success(ncservice.selncall(u));
    }
    //    新增奶茶
    @PostMapping("/addnc")
    public Result<?> addnc(@RequestBody Ncpljl ncpljl){
        return Result.success(ncservice.addnc(ncpljl));
    }
    //    修改奶茶信息
    @PostMapping("/upnc")
    public Result<?> upnc(@RequestBody Ncpljl ncpljl){
        return Result.success(ncservice.upnc(ncpljl));
    }
    //    根据id删除对应奶茶
    @DeleteMapping("/delnc")
    public Result<?> delnc(@RequestParam String i){
        return Result.success(ncservice.delnc(i));
    }
}
