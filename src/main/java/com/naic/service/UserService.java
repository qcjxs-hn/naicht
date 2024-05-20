package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.common.Result;
import com.naic.entity.User;
import com.naic.entity.Userrecord;
import com.naic.entity.Yhq;
import com.naic.mapper.Usermapper;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserrecordService userrecordService;

    @Resource
    private Usermapper usermapper;

    @Resource
    private AdminService adminService;
    //创建用户
    public int createUser(String p) {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        User user=new User();
        user.setUser(p);
        user.setUserbr(java.sql.Date.valueOf(sdf.format(date)));
        user.setUsersex("");
        user.setGxqm("签到领权益！");
        user.setHydj("");
        user.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));
        user.setUserjyz(0);
        user.setPassword("");
        user.setUserjlid(date.getTime()+"");
        user.setNickname("");
        user.setYhj("");
        user.setDkb("");
        user.setUserqdcx(0);
        user.setUserzt("0");
        user.setUserdlzt("0");
        return  usermapper.insert(user);

    }
    //根据用户名修改登录状态
    public void updlbyuser(String u){
        UpdateWrapper<User> updatawrapper1 = new UpdateWrapper<>();
        updatawrapper1.eq("user",u).set("userdlzt",1);
        usermapper.update(null,updatawrapper1);
    }

    //根据用户名(手机号)查询用户信息
    public Result<?> seluser(String u){
        LambdaQueryWrapper<User> la1= Wrappers.<User>lambdaQuery();
        la1.eq(User::getUser,u);
        return Result.success(usermapper.selectOne(la1));
    }
    //根据账号查询用户信息
    public User selu(String u){
        LambdaQueryWrapper<User> la1= Wrappers.<User>lambdaQuery();
        la1.eq(User::getUser,u);
        return usermapper.selectOne(la1);
    }
    //根据账号和优惠券id减去优惠券
    public void upbyuserandqid(String u,String q){
        User user=selu(u);
        if(user.getYhj().contains(q+";")){
            UpdateWrapper<User> updatawrapper = new UpdateWrapper<>();
            updatawrapper.eq("user",u).set("yhj",user.getYhj().replace(q+";",""));
            usermapper.update(null,updatawrapper);
        }

    }
    //根据用户名进行封号或解封
    public Result<?> upbyusertitle(String u){
        User user=selu(u);
        UpdateWrapper<User> updatawrapper = new UpdateWrapper<>();
        if(user.getUserzt().equals("0")){
            updatawrapper.eq("user",u).set("userzt","2");
            usermapper.update(null,updatawrapper);
            return Result.success("2");
        }else{
            updatawrapper.eq("user",u).set("userzt","0");
            usermapper.update(null,updatawrapper);
            return Result.success("0");
        }

    }
    //根据用户名进行个人信息修改
    public int upuserxxbyuser(User ubody,String u){
        User user=selu(u);
        Userrecord userrecord=userrecordService.seluserjl(user.getUser());
        int i=0;
        //判断昵称是否修改
//        System.out.println(ubody.getNickname());
        if(!ubody.getNickname().equals("")) {
            UpdateWrapper<User> updatawrapper = new UpdateWrapper<>();
            updatawrapper.eq("user", u).set("nickname", ubody.getNickname());
            usermapper.update(null, updatawrapper);
            i += 1;
        }
        //判断个性签名是否修改
//        System.out.println(ubody.getGxqm());
        if(!ubody.getGxqm().equals("")){
            UpdateWrapper<User> updatawrapper2 = new UpdateWrapper<>();
            updatawrapper2.eq("user",u).set("gxqm",ubody.getGxqm());
            i+=1;
            usermapper.update(null,updatawrapper2);
        }
        //判断生日是否修改
//        System.out.println(ubody.getUserbr());
        if(ubody.getUserbr()!=null){
            if(userrecord.getSfxggbr().equals("0")) {
                UpdateWrapper<User> updatawrapper3 = new UpdateWrapper<>();
                updatawrapper3.eq("user", u).set("userbr", ubody.getUserbr());
                userrecordService.upuserjlbyuser(u,1);
                i += 1;
                usermapper.update(null,updatawrapper3);
            }else{
                return i=10;
            }
        }
        //判断性别是否修改
//        System.out.println(ubody.getUsersex());
        if(!ubody.getUsersex().equals("")){
            if(userrecord.getSfxggxb().equals("0")) {
                UpdateWrapper<User> updatawrapper1 = new UpdateWrapper<>();
                updatawrapper1.eq("user",u).set("usersex",ubody.getUsersex());
                userrecordService.upuserjlbyuser(u,0);
                usermapper.update(null,updatawrapper1);
                i+=1;
            }else{
                return i=9;
            }
        }
        return i;
    }
    //用户签到
    public int upqdbyuser(String u,String q){
        User user=selu(u);
        if(q.equals("0")){
            UpdateWrapper<User> updatawrapper1 = new UpdateWrapper<>();
            updatawrapper1.eq("user",u).set("userqdcx",1).set("userjyz",user.getUserjyz()+10);
           int i= usermapper.update(null,updatawrapper1);
            return i;
        }else {
            return 0;
        }
    }
    //    用户签到更新(每天)
    public int  upqdgxbyuser(String u){
        User user=selu(u);
        if(user.getUserqdcx()==1){
            UpdateWrapper<User> updatawrapper1 = new UpdateWrapper<>();
            updatawrapper1.eq("user",u).set("userqdcx",0);
            int i= usermapper.update(null,updatawrapper1);
            return i;
        }else {
            return 0;
        }
    }
//==========================后台===========================================
//       超级管理员全查
    public List<User> selall(String u){
        if(adminService.selu(u)!=null) {
            if(adminService.selu(u).getUserzt().equals("3")) {
                List<User> users = usermapper.selectList(null);
                for (int i = 0; i < users.size(); i++) {
                    users.get(i).setPassword("");
//                    users.get(i).setUserbr(java.sql.Date.valueOf("1970-01-01"));
//                    users.get(i).setUserjyz(0);
//                    users.get(i).setUsersex("");
                }
                return users;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    //    根据user删除用户
    public int deluser(String u){
        LambdaQueryWrapper<User> wrapper=Wrappers.<User>lambdaQuery();
        wrapper.eq(User::getUser,u);
        return usermapper.delete(wrapper);
    }
//    根据user更新用户
    public int updatauser(User user){
        LambdaQueryWrapper<User> wrapper=Wrappers.<User>lambdaQuery();
        wrapper.eq(User::getUser,user.getUser());
        return usermapper.update(user,wrapper);
    }
//    超级管理员添加用户
    public int adduser(User user){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(selu(user.getUser())==null){
            user.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));
            user.setUserjlid(date.getTime()+"");
            return usermapper.insert(user);
        }else{
            return 0;
        }
    }
}
