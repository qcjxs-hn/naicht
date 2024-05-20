package com.naic.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.naic.entity.Ncdd;
import com.naic.entity.Ncgg;
import com.naic.entity.User;
import com.naic.mapper.Ncggmapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class Ncggservice {
    @Resource
    private Ncggmapper ncggmapper;
    @Resource
    private AdminService adminService;
    //根据id查询规格
    public Ncgg getncgg(String id){
        LambdaQueryWrapper<Ncgg> wrapper= Wrappers.<Ncgg>lambdaQuery();
        wrapper.eq(Ncgg::getNcid,id);
        return ncggmapper.selectOne(wrapper);
    }
    //==========================后台===========================================
 //       超级管理员奶茶规格全查
   public List<Ncgg> selggbysupername(String u){
       if(adminService.selu(u)!=null) {
           if (adminService.selu(u).getUserzt().equals("3")) {
               return ncggmapper.selectList(null);
           }else{
               return null;
           }
       }else{
           return null;
       }
   }
    //    根据id删除规格
    public int delncgg(String i) {
        LambdaQueryWrapper<Ncgg> wrapper=Wrappers.<Ncgg>lambdaQuery();
        wrapper.eq(Ncgg::getNcid,i);
        return ncggmapper.delete(wrapper);
    }
    //    根据id更新规格
    public int updatancgg(Ncgg ncgg){
        LambdaQueryWrapper<Ncgg> wrapper=Wrappers.<Ncgg>lambdaQuery();
        wrapper.eq(Ncgg::getNcid,ncgg.getNcid());
        return ncggmapper.update(ncgg,wrapper);
    }
    //    超级管理员添加规格
    public int addncgg(String u,Ncgg ncgg){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(adminService.selu(u)!=null) {
            if (adminService.selu(u).getUserzt().equals("3")) {
                return ncggmapper.insert(ncgg);
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }
}
