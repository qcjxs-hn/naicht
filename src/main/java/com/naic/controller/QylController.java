package com.naic.controller;

import com.naic.common.Result;
import com.naic.entity.Gn;
import com.naic.entity.Qyl;
import com.naic.service.QylService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/q")
public class QylController {
    @Resource
    QylService qylService;

    //首页权益栏
    @GetMapping("/all")
    public Result<?> getqylall(){
        return Result.success(qylService.getqyl());
    }

    //    ========================后台========================
    //    新增功能
    @PostMapping("/addqyl")
    public Result<?> addqyl(@RequestBody Qyl qyl){return Result.success(qylService.addqyl(qyl));}
    //    修改功能信息
    @PostMapping("/upqyl")
    public Result<?> upqyl(@RequestBody  Qyl qyl){return Result.success(qylService.upqyl(qyl));}
    //    根据id删除对应功能
    @DeleteMapping("/delqyl")
    public Result<?> delqyl(@RequestParam String t){return Result.success(qylService.delqyl(t));}

}
