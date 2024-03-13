package com.naic.controller;

import com.naic.common.Result;
import com.naic.entity.Cjgl;
import com.naic.entity.User;
import com.naic.mapper.Usermapper;
import com.naic.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin")
public class Admincontroller {
    @Resource
    private AdminService adminService;

    @GetMapping("/gc")
    public Result<?> getcode(){
        return Result.success(adminService.getcode());
    }
//    扫码返回和注册，u为用户，c为获取的代码
    @PostMapping("/rc")
    public Result<?> ScanCodeAndRegister(@RequestParam String u,@RequestParam String c){
        return Result.success(adminService.smhzc(u,c));
    }

//    根据code查询登录池User
    @GetMapping("/cu")
    public Result<?> seluserbycode(@RequestParam String c){
        if(c.equals("")) {
            return Result.error("-1",null);
        }else{
            String user = adminService.selubycode(c);
            if(user!=null) {
                if (user.equals("错误") ) {
                    adminService.deldlhccbycide(c);
                    return Result.error("-1", "禁止多人扫码！");
                } else {
                    adminService.deldlhccbycide(c);
                    return Result.success(user);
                }
            }else{
                return Result.error("-1",null);
            }
        }
    }

    //根据user查询登录
    @GetMapping("/cadl")
    public Result<?> CheckAdminLogin(@RequestParam String u){
        return Result.success(adminService.seluser(u));
    }

    //    超级管理员登录
    @PostMapping("/sal")
    public Result<?> Superadminlogin(@RequestBody Cjgl user){
        System.out.println(user);
        return Result.success(adminService.superadminlogin(user));
    }
}
