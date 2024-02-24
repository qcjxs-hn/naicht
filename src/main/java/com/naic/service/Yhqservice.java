package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.entity.Yhq;
import com.naic.mapper.Yhqmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Yhqservice {

    @Resource
    private Yhqmapper yhqmapper;

    //根据id获取优惠券信息
    public Yhq getyhq(String q){
//        System.out.println(q);
        LambdaQueryWrapper<Yhq> wrapper= Wrappers.<Yhq>lambdaQuery();
        wrapper.eq(Yhq::getQid,q);
        return yhqmapper.selectOne(wrapper);
    }
    //根据id修改优惠券信息
    public void upbyid(String q){
//        System.out.println(q);
        UpdateWrapper<Yhq> updatawrapper = new UpdateWrapper<>();
        updatawrapper.eq("qid",q).set("yhqsfsy",'1');
        yhqmapper.update(null,updatawrapper);
    }
}
