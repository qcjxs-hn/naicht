package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.entity.Ncpl;
import com.naic.entity.Ncpljl;
import com.naic.entity.User;
import com.naic.mapper.Ncmapper;
import com.naic.mapper.Ncplmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class Ncservice {
    @Resource
    private Ncmapper ncmapper;

    @Resource
    private Ncplmapper ncplmapper;

    @Resource
    private AdminService adminService;
    //获取侧边栏
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
    //==========================后台===========================================

//    新增奶茶
    public int addnc(Ncpljl nc){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LambdaQueryWrapper<Ncpljl> wrapper=Wrappers.<Ncpljl>lambdaQuery();
        wrapper.eq(Ncpljl::getNcmz,nc.getNcmz());
        if(ncmapper.selectOne(wrapper)==null){
            nc.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));
            nc.setCreateid(date.getTime()+"");
            return ncmapper.insert(nc);
        }else{
            return 0;
        }
    }
//   超级管理员全查
public List<Ncpljl> selncall(String u){
    if(adminService.selu(u)!=null) {
        if(adminService.selu(u).getUserzt().equals("3")) {
            List<Ncpljl> ncs = ncmapper.selectList(null);
            return ncs;
        }else{
            return null;
        }
    }else{
        return null;
    }
}
    //    修改奶茶信息
    public int upnc(Ncpljl ncpljl){
        LambdaQueryWrapper<Ncpljl> wrapper=Wrappers.<Ncpljl>lambdaQuery();
        wrapper.eq(Ncpljl::getCreateid,ncpljl.getCreateid());
        return ncmapper.update(ncpljl,wrapper);
    }
    //    根据id删除对应奶茶
    public int delnc(String i){
        LambdaQueryWrapper<Ncpljl> wrapper=Wrappers.<Ncpljl>lambdaQuery();
        wrapper.eq(Ncpljl::getCreateid,i);
        return ncmapper.delete(wrapper);
    }

//    新增侧边栏
    public int addncplcbl(Ncpl ncpl){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LambdaQueryWrapper<Ncpl> wrapper=Wrappers.<Ncpl>lambdaQuery();
        wrapper.eq(Ncpl::getNcpl,ncpl.getNcpl());
        if(ncplmapper.selectOne(wrapper)==null){
            ncpl.setNcpl((ncplmapper.selectList(null).size())+"");
            ncpl.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));
            ncpl.setCreateid(date.getTime()+"");
            return ncplmapper.insert(ncpl);
        }else{
            return 0;
        }
    }

//    修改侧边栏
    public int upncplcbl(Ncpl ncpl){
        LambdaQueryWrapper<Ncpl> wrapper=Wrappers.<Ncpl>lambdaQuery();
        wrapper.eq(Ncpl::getCreateid,ncpl.getCreateid());
        return ncplmapper.update(ncpl,wrapper);
    }
//    根据id删除对应侧边栏
    public int delncplcbl(String i){
        LambdaQueryWrapper<Ncpl> wrapper=Wrappers.<Ncpl>lambdaQuery();
        wrapper.eq(Ncpl::getCreateid,i);
        return ncplmapper.delete(wrapper);
    }
}
