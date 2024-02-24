package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.entity.User;
import com.naic.entity.Userrecord;
import com.naic.mapper.Userrecordmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserrecordService {
    @Resource
    private Userrecordmapper userrecordmapper;
//    创建用户记录
    public int CreateUserRecord(String p){
        Userrecord userrecord=new Userrecord();
        Date date=new Date();
        userrecord.setUser(p);
        userrecord.setSfxggxb("0");
        userrecord.setSfxggbr("0");
        userrecord.setUserjlid(date.getTime()+"");
       return userrecordmapper.insert(userrecord);
    }

    //根据用户名(手机号)查询用户记录
    public Userrecord  seluserjl(String u){
        LambdaQueryWrapper<Userrecord> la1= Wrappers.<Userrecord>lambdaQuery();
        la1.eq(Userrecord::getUser,u);
        return userrecordmapper.selectOne(la1);
    }
    //根据用户名(手机号)修改用户记录 u用户 id 对应处理,
    public int upuserjlbyuser(String u, int id){
//        System.out.println(u);
//        System.out.println(id);
        if(id==0) {
            UpdateWrapper<Userrecord> updatawrapper1 = new UpdateWrapper<>();
            updatawrapper1.eq("user", u).set("sfxggxb", "1");
            return userrecordmapper.update(null,updatawrapper1);

        }else{
            UpdateWrapper<Userrecord> updatawrapper = new UpdateWrapper<>();
            updatawrapper.eq("user", u).set("sfxggbr", "1");
            return userrecordmapper.update(null,updatawrapper);
        }
    }
}
