package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.entity.Wj;
import com.naic.mapper.Wjmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WjService {
    @Resource
    private Wjmapper wjmapper;

    public Wj getwjbyti(String ti){
        LambdaQueryWrapper<Wj> wrapper= Wrappers.<Wj>lambdaQuery();
        wrapper.eq(Wj::getWjtitle,ti);
        return wjmapper.selectOne(wrapper);
    }
}
