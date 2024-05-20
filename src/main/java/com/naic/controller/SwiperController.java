package com.naic.controller;

import com.naic.common.Result;
import com.naic.entity.Swiper;
import com.naic.service.Swiperserivce;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//展播栏
@RestController
@RequestMapping("/sw")
public class SwiperController {
    @Resource
    private Swiperserivce swiperserivce;
//查询所有
    @GetMapping("/all")
    public Result<?> getswiperall(){
        return Result.success(swiperserivce.getswiper());
    }
//新增展播栏
    @PostMapping("xz")
    public Result<?> swxz(@RequestBody Swiper swiper){
        return Result.success(swiperserivce.inswiper(swiper));
    }
    //    更新对应展播栏
    @PostMapping("/gx")
    public Result<?> swgx(@RequestBody Swiper swiper){
        return Result.success(swiperserivce.upswiper(swiper));
    }
//    删除对应展播栏
    @PostMapping("/del")
    public Result<?> swdel(@RequestParam int id){
        return Result.success(swiperserivce.delswiper(id));
    }
}
