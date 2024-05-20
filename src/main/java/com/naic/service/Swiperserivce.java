package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.common.Result;
import com.naic.entity.Swiper;
import com.naic.mapper.Swipermapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class Swiperserivce {
    @Resource
    private Swipermapper swipermapper;

    public List<Swiper> getswiper(){
        return swipermapper.selectList(null);
    }

    public int inswiper(Swiper s){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        s.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));
        return swipermapper.insert(s);
    }

    public int upswiper(Swiper s){
        return swipermapper.updateById(s);
    }

    public int delswiper(int i){
        LambdaQueryWrapper<Swiper> wrapper= Wrappers.<Swiper>lambdaQuery();
        wrapper.eq(Swiper::getId,i);
        return swipermapper.delete(wrapper);
    }
}
