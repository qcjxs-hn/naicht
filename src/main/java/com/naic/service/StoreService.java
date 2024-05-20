package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.naic.entity.Ncpljl;
import com.naic.entity.Storeinfo;
import com.naic.mapper.Storemapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StoreService {
    @Resource
    Storemapper storemapper;
    @Resource
    private AdminService adminService;
    //查询附近店铺
    public Page<Storeinfo> getfjstore(@RequestParam String dw){
        LambdaQueryWrapper<Storeinfo> wrapper= Wrappers.<Storeinfo>lambdaQuery();
        LambdaQueryWrapper<Storeinfo> wrapper1= Wrappers.<Storeinfo>lambdaQuery();
        LambdaQueryWrapper<Storeinfo> wrapper2= Wrappers.<Storeinfo>lambdaQuery();
        LambdaQueryWrapper<Storeinfo> wrapper3= Wrappers.<Storeinfo>lambdaQuery();
        wrapper.like(Storeinfo::getDpwz,dw);
        Page<Storeinfo> res = storemapper.selectPage(new Page<>(), wrapper);
//        System.out.println("res"+res.getRecords());
        if(res.getRecords().isEmpty()){
//           //二分地址距离最近
            wrapper1.like(Storeinfo::getDpwz,dw.substring(0,dw.length()/2+(int)(dw.length()/2)/2));
            Page<Storeinfo> res1 = storemapper.selectPage(new Page<>(1,9), wrapper1);
//            System.out.println(dw.substring(0,dw.length()/2+(int)(dw.length()/2)/2));
//            System.out.println("res1"+res1.getRecords());
            if(res1.getRecords().isEmpty()){
                //二分地址距离远离
                wrapper2.like(Storeinfo::getDpwz,dw.substring(0,dw.length()/2+(int)((int)(dw.length()/2)/2)/2));
                Page<Storeinfo> res2 = storemapper.selectPage(new Page<>(), wrapper2);
//                System.out.println(dw.substring(0,dw.length()/2+(int)((int)(dw.length()/2)/2)/2));
//                System.out.println("res2"+res2.getRecords());
                if(res2.getRecords().isEmpty()){
                    //二分地址距离最远
                    wrapper3.like(Storeinfo::getDpwz,dw.substring(0,dw.length()/2));

                    Page<Storeinfo> res3 = storemapper.selectPage(new Page<>(), wrapper3);
//                    System.out.println(dw.substring(0,dw.length()/2));
//                    System.out.println("res3"+res3.getRecords());
                    return res3;
                }else{
                    return res2;
                }
            }
            else{
//                System.out.println(res1.getTotal());
                List<Storeinfo> filteredRecords = new ArrayList<>();
                for (Storeinfo record : res1.getRecords()) {
                    if (!record.getDpzt().equals("1")) {
                        // 如果 dpzt 不等于 "1"，则跳过当前记录
                        continue;
                    }
                    filteredRecords.add(record);
                }
                res1.setRecords(filteredRecords);
                // 更新 res1 总记录数为筛选后结果集的大小
                res1.setTotal(filteredRecords.size());
                return res1;

            }
        }else{
            return res;
        }



    }
    //根据店铺名称查询店铺信息
    public Storeinfo getdpbyname(String d) {
        LambdaQueryWrapper<Storeinfo> wrapper= Wrappers.<Storeinfo>lambdaQuery();
        wrapper.eq(Storeinfo::getDpmc,d);
        return storemapper.selectOne(wrapper);
    }
//    ==========================后台================================


    //    新增奶茶
    public int addstore(Storeinfo storeinfo){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LambdaQueryWrapper<Storeinfo> wrapper=Wrappers.<Storeinfo>lambdaQuery();
        wrapper.eq(Storeinfo::getDpmc,storeinfo.getDpmc());
        if(storemapper.selectOne(wrapper)==null){
            storeinfo.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));
            storeinfo.setCreateid(date.getTime()+"");
            return storemapper.insert(storeinfo);
        }else{
            return 0;
        }
    }
    //超级管理员全查店铺
    public List<Storeinfo> selstoreall(String u){
        if(adminService.selu(u)!=null) {
            if(adminService.selu(u).getUserzt().equals("3")) {
                List<Storeinfo> stores = storemapper.selectList(null);
                return stores;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    //    修改奶茶信息
    public int upstore(Storeinfo storeinfo){
        LambdaQueryWrapper<Storeinfo> wrapper=Wrappers.<Storeinfo>lambdaQuery();
        wrapper.eq(Storeinfo::getCreateid,storeinfo.getCreateid());
        return storemapper.update(storeinfo,wrapper);
    }
    //    根据id删除对应奶茶
    public int delstore(String i){
        LambdaQueryWrapper<Storeinfo> wrapper=Wrappers.<Storeinfo>lambdaQuery();
        wrapper.eq(Storeinfo::getCreateid,i);
        return storemapper.delete(wrapper);
    }


}
