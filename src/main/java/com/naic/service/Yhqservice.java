package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.entity.Storeinfo;
import com.naic.entity.Yhq;
import com.naic.mapper.Yhqmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class Yhqservice {

    @Resource
    private Yhqmapper yhqmapper;

    @Resource
    private AdminService adminService;

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
//    ===============================后台==============================

    //    新增优惠卷
    public int addyhj(Yhq yhq){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LambdaQueryWrapper<Yhq> wrapper=Wrappers.<Yhq>lambdaQuery();
        wrapper.eq(Yhq::getQid,yhq.getQid());
        if(yhqmapper.selectOne(wrapper)==null){
            yhq.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));
            yhq.setQid(date.getTime()+"");
            return yhqmapper.insert(yhq);
        }else{
            return 0;
        }
    }
    //超级管理员全查优惠卷
    public List<Yhq> selyhjall(String u){
        if(adminService.selu(u)!=null) {
            if(adminService.selu(u).getUserzt().equals("3")) {
                List<Yhq> yhqs = yhqmapper.selectList(null);
                return yhqs;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    //    修改优惠卷信息
    public int upyhj(Yhq yhq){
        LambdaQueryWrapper<Yhq> wrapper=Wrappers.<Yhq>lambdaQuery();
        wrapper.eq(Yhq::getQid,yhq.getQid());
        return yhqmapper.update(yhq,wrapper);
    }
    //    根据id删除对应优惠卷
    public int delyhj(String i){
        LambdaQueryWrapper<Yhq> wrapper=Wrappers.<Yhq>lambdaQuery();
        wrapper.eq(Yhq::getQid,i);
        return yhqmapper.delete(wrapper);
    }

}
