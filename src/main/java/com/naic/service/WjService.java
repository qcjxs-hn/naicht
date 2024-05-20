package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.entity.Qyl;
import com.naic.entity.User;
import com.naic.entity.Wj;
import com.naic.mapper.Wjmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class WjService {
    @Resource
    private Wjmapper wjmapper;

    @Resource
    private AdminService adminService;
    //    根据名字查询说明文件
    public Wj getwjbyti(String ti){
        LambdaQueryWrapper<Wj> wrapper= Wrappers.<Wj>lambdaQuery();
        wrapper.eq(Wj::getWjtitle,ti);
        return wjmapper.selectOne(wrapper);
    }
//    =========================================后台=================================
    //超级管理员全查说明文件
    public List<Wj> selall(String u){
        if(adminService.selu(u)!=null) {
            if(adminService.selu(u).getUserzt().equals("3")) {
                List<Wj> wjs = wjmapper.selectList(null);
                return wjs;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    //    根据createid删除
    public int delwj(String i){
        LambdaQueryWrapper<Wj> wrapper=Wrappers.<Wj>lambdaQuery();
        wrapper.eq(Wj::getCreateid,i);
        return wjmapper.delete(wrapper);
    }
    //    根据id更新文件
    public int upwj(Wj wj){
        LambdaQueryWrapper<Wj> wrapper=Wrappers.<Wj>lambdaQuery();
        wrapper.eq(Wj::getCreateid,wj.getCreateid());
        return wjmapper.update(wj,wrapper);
    }
    //    超级管理员添加文件
    public int addwj(String u,Wj wj){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LambdaQueryWrapper<Wj> wrapper= Wrappers.<Wj>lambdaQuery();
        wrapper.eq(Wj::getCreateid,wj.getCreateid());
        if(adminService.selu(u)!=null) {
            if (adminService.selu(u).getUserzt().equals("3")) {
                if (wjmapper.selectOne(wrapper) == null) {
                    wj.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));
                    wj.setCreateid(date.getTime() + "");
                    return wjmapper.insert(wj);
                } else {
                    return 0;
                }
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }
}
