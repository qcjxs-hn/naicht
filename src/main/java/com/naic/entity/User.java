package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("user")
public class User {
    private int id;
    private String user;//用户名
    private String password;//密码
    private String nickname;//名称
    private Date userbr;//用户生日
    private String usersex;//用户性别
    private String gxqm;//个性签名
    private String hydj;//会员等级
    private String yhj;//优惠卷
    private String dkb;//抵扣币
    private String userjlid; //用户记录表id
    private int userjyz; //用户经验值
    private int userqdcx; //用户签到
    private Date createdate; //创建时间
    private String userzt; //用户状态0为正常,2为封号，0代表正常用户，1代表普通后台管理员，3代表超级管理员,4为管理员账号审核
    private String userdlzt;//0为离线，1为登录
}
