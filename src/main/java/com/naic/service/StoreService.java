package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.naic.entity.Storeinfo;
import com.naic.mapper.Storemapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Service
public class StoreService {
    @Resource
    Storemapper storemapper;

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
            Page<Storeinfo> res1 = storemapper.selectPage(new Page<>(), wrapper1);
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
                return res1;
            }
        }else{
            return res;
        }



    }

    public Storeinfo getdpbyname(String d) {
        LambdaQueryWrapper<Storeinfo> wrapper= Wrappers.<Storeinfo>lambdaQuery();
        wrapper.eq(Storeinfo::getDpmc,d);
        return storemapper.selectOne(wrapper);
    }
}
