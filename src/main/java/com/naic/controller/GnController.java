package com.naic.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.common.Result;
import com.naic.entity.Gn;
import com.naic.entity.Yhq;
import com.naic.entity.Yhzyl;
import com.naic.service.GnService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

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

//    ========================后台========================
    //    新增功能
    @PostMapping("/addgn")
    public Result<?> addgn(@RequestBody Gn gn){return Result.success(gnService.addgn(gn));}
    //    修改功能信息
    @PostMapping("/upgn")
    public Result<?> upgn(@RequestBody  Gn gn){return Result.success(gnService.upgn(gn));}
    //    根据id删除对应功能
    @DeleteMapping("/delgn")
    public Result<?> delgn(@RequestParam String i){return Result.success(gnService.delgn(i));}

    //    新增我的资源栏
    @PostMapping("/addmygn")
    public Result<?> addmygn(@RequestBody Yhzyl yhzyl){return Result.success(gnService.addmygn(yhzyl));}
    //    修改我的资源栏信息
    @PostMapping("/upmygn")
    public Result<?> upmygn(@RequestBody  Yhzyl yhzyl){return Result.success(gnService.upmygn(yhzyl));}
    //    根据id删除对应我的资源栏
    @DeleteMapping("/delmygn")
    public Result<?> delmygn(@RequestParam String i){return Result.success(gnService.delmygn(i));}
}
