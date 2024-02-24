package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.entity.Ncpl;
import com.naic.entity.Ncpljl;
import com.naic.mapper.Ncmapper;
import com.naic.mapper.Ncplmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Ncservice {
    @Resource
    private Ncmapper ncmapper;

    @Resource
    private Ncplmapper ncplmapper;


    public List<Ncpl> getncplcbl(){

        return ncplmapper.selectList(null);


    }
    //根据id查询奶茶
    public Ncpljl selbyid(String ncid) {
        LambdaQueryWrapper<Ncpljl> wrapper= Wrappers.<Ncpljl>lambdaQuery();
        wrapper.eq(Ncpljl::getCreateid,ncid).orderByDesc(Ncpljl::getNcpl);
        return ncmapper.selectOne(wrapper);
    }
    //根据名字查询奶茶
    public Ncpljl selbyname(String ncname){
        LambdaQueryWrapper<Ncpljl> wrapper=Wrappers.<Ncpljl>lambdaQuery();
        wrapper.eq(Ncpljl::getNcmz,ncname);
        return ncmapper.selectOne(wrapper);
    }
}
