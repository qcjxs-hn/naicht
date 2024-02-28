package com.naic.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.naic.common.Result;
import com.naic.common.WeChatSessionInfo;
import com.naic.common.Wechattophone;
import com.naic.entity.User;
import com.naic.entity.Wachatjh;
import com.naic.entity.Yhq;
import com.naic.service.UserService;
import com.naic.service.UserrecordService;
import com.naic.service.WeChatService;
import com.naic.service.Yhqservice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    WeChatService weChatService;

    @Resource
    UserService userService;

    @Resource
    Yhqservice yhqservice;

    @Resource
    UserrecordService userrecordService;
    private Yhq[] qsz;

    //微信手机号登录
    @PostMapping("/login")
    public Result<?> wechatlogin(@RequestBody Wachatjh w){
        try {
            WeChatSessionInfo fhinfo= (WeChatSessionInfo) weChatService.convertStringToWeChatSessionInfo(weChatService.code2Session(w.getCode())+"");
            System.out.println(fhinfo.getSessionKey());
            String encryptedData = w.getEncryptedData();
            String sessionKey = fhinfo.getSessionKey();
            String iv = w.getIv();
            String phoneNumber = Wechattophone.decryptPhoneNumber(encryptedData, sessionKey, iv);
            String phone=phoneNumber.split(",")[0].split(":")[1];
            System.out.println(phone.substring(1,phone.length()-1));
            System.out.println("电话号" + phoneNumber);
            Result<?> user=userService.seluser(phone.substring(1,phone.length()-1));
            if(user.data==null) {
                userService.updlbyuser(phone.substring(1, phone.length() - 1));
                createuser(phone.substring(1, phone.length() - 1));
                createrecord(phone.substring(1, phone.length() - 1));
                return Result.success(seluser(phone.substring(1, phone.length() - 1)));
            }else{
                userService.updlbyuser(phone.substring(1, phone.length() - 1));
                return Result.success(seluser(phone.substring(1, phone.length() - 1)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(w.getIv());

        return Result.success();
    }
//    创建用户
    public Result<?> createuser(String phone){
        return Result.success(userService.createUser(phone));
    }
//    创建用户记录
    public Result<?> createrecord(String phone){return Result.success(userrecordService.CreateUserRecord(phone));}
//    根据用户名(手机号)查询用户信息
    @GetMapping("/cx")
    public Result<?> seluser(String u) {
        return Result.success(userService.seluser(u));
    }
//    获取用户优惠券
    @GetMapping("/getyhq")
    public Result<?> selyhq(@RequestParam String q){
        if(q!="") {
            String[] qz = q.split(";");
            qsz = new Yhq[qz.length]; // 初始化数组
            for (int i = 0; i <qz.length; i++) {
//                System.out.println(yhqservice.getyhq(qz[i]));
                qsz[i] = yhqservice.getyhq(qz[i]); // 将值写入数组
            }
        }
        return Result.success(qsz);
    }
//    根据用户名进行个人信息修改
    @PostMapping("/gx")
    public Result<?> upuxbyuser(@RequestBody User user,@RequestParam String u){
        int i=userService.upuserxxbyuser(user,u);
        if(i==9) {
            return Result.error("-1", "性别只能修改一次！");
        }
        else if(i==10){
            return Result.error("-2", "生日只能修改一次！");
        }
        else if(i>0&&i<9){
            return Result.success("修改成功！");
        }else{
            return Result.success("修改失败！");
        }
    }
//    用户签到
    @PostMapping("/qd")
    public Result<?> upqdbyuser(@RequestParam String u,@RequestParam String q){
        return  Result.success(userService.upqdbyuser(u,q));
    }
}
