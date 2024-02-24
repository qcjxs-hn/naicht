package com.naic.controller;

import com.naic.common.Result;
import com.naic.service.Swiperserivce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//展播栏
@RestController
@RequestMapping("/sw")
public class SwiperController {
    @Resource
    private Swiperserivce swiperserivce;

    @GetMapping("/all")
    public Result<?> getswiperall(){
        return Result.success(swiperserivce.getswiper());
    }

}
