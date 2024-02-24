package com.naic.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.entity.Ncgg;
import com.naic.mapper.Ncggmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Ncggservice {
    @Resource
    private Ncggmapper ncggmapper;
    //根据id查询规格
    public Ncgg getncgg(String id){
        LambdaQueryWrapper<Ncgg> wrapper= Wrappers.<Ncgg>lambdaQuery();
        wrapper.eq(Ncgg::getNcid,id);
        return ncggmapper.selectOne(wrapper);
    }
}
