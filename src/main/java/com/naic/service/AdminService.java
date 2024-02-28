package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.common.Result;
import com.naic.entity.AdminLoginhcc;
import com.naic.entity.User;
import com.naic.mapper.Adminmapper;
import com.naic.mapper.Usermapper;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class AdminService {
    @Resource
    private Usermapper usermapper;

    @Resource
    private Adminmapper adminmapper;

    private String lccode="111";

    public String getcode(){
        // 生成12位随机数和字母的组合
        lccode=generateRandomString(12);
        return lccode;
    }
    public int smhzc(String u ,String c){
        System.out.println(u);
        System.out.println(c);
        System.out.println(lccode);
        LambdaQueryWrapper<User> wrapper= Wrappers.<User>lambdaQuery();
        wrapper.eq(User::getUser,u);
        User user=usermapper.selectOne(wrapper);
//        判断是否正常
        if(user!=null) {
//            判断返回码
            if (c.equals(lccode)) {
                if(user.getUserzt().equals("1")) {
                    int i1=0;
                    AdminLoginhcc adminLoginhcc=new AdminLoginhcc();
                    adminLoginhcc.setCode(lccode);
                    adminLoginhcc.setUser(user.getUser());
                    i1 +=adminmapper.insert(adminLoginhcc);
                    i1 +=uploginbyuser(user.getUser());
                    System.out.println(i1);
                    return  i1;
                }else{
                    return 4;
                }
            }else{
                return 3;
            }
        }else{
            if (c.equals(lccode)) {
                User user1=new User();
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                user1.setUser(u);
                user1.setUserbr(java.sql.Date.valueOf(sdf.format(date)));
                user1.setUsersex("");
                user1.setGxqm("签到领权益！");
                user1.setHydj("");
                user1.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));
                user1.setUserjyz(0);
                user1.setPassword("");
                user1.setUserjlid(date.getTime() + "");
                user1.setNickname("");
                user1.setYhj("");
                user1.setDkb("");
                user1.setUserqdcx(0);
                user1.setUserzt("4");
                user1.setUserdlzt("0");
                return  usermapper.insert(user1);
            }else{
                return 3;
            }

        }
    }
    //admin登录
    public int uploginbyuser(String u){
        UpdateWrapper<User> wrapper=new UpdateWrapper<>();
        wrapper.eq("user",u).set("userdlzt",1);
        int i =usermapper.update(null,wrapper);
        return i;
    }
//    超级管理员登录
    public Result<?>  superadminlogin(User u){
        LambdaQueryWrapper<User> wrapper =Wrappers.<User>lambdaQuery();
        wrapper.eq(User::getUser,u.getUser());
        User user=usermapper.selectOne(wrapper);
        if(user!=null){
            if(u.getPassword().equals(user.getPassword())){
                uploginbyuser(user.getUser());
                user.setPassword("");
                return Result.success(user);
            }else{
                return Result.error("-2","密码错误！");
            }
        }else{
            return Result.error("-1","没有这个超级管理员账号");
        }

    }
    //根据code查询登录
    public String selubycode(String c){
        LambdaQueryWrapper<AdminLoginhcc> wrapper=Wrappers.<AdminLoginhcc>lambdaQuery();
        wrapper.eq(AdminLoginhcc::getCode,c);
        try {
            AdminLoginhcc adminLoginhcc = adminmapper.selectOne(wrapper);
            if (adminLoginhcc != null) {
                return adminLoginhcc.getUser();
            }else{
                return null;

            }
        }catch (TooManyResultsException e){
            return "错误";
        }

    }
    //根据code删除登录缓存池记录
    public void deldlhccbycide(String c){
        LambdaQueryWrapper<AdminLoginhcc> wrapper=Wrappers.<AdminLoginhcc>lambdaQuery();
        wrapper.eq(AdminLoginhcc::getCode,c);
        adminmapper.delete(wrapper);
    }
    //根据用户名查询登录状态返回
    public Result<?> seluser(String u){
        LambdaQueryWrapper<User> la1= Wrappers.<User>lambdaQuery();
        la1.eq(User::getUser,u);
        return Result.success(usermapper.selectOne(la1));
    }
    // 生成指定长度的随机字符串
    public static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String combined = characters + characters.toUpperCase() + numbers;

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(combined.length());
            sb.append(combined.charAt(index));
        }

        return sb.toString();
    }
}
