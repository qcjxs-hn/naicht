package com.naic.service;

import com.naic.common.Result;
import com.naic.entity.Swiper;
import com.naic.mapper.Swipermapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Swiperserivce {
    @Resource
    private Swipermapper swipermapper;

    public List<Swiper> getswiper(){
        return swipermapper.selectList(null);
    }
}
