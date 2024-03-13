package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
@Data
@TableName("cjgl")
public class Cjgl {
    private int id;
    private String user;//用户名
    private String password;//密码
    private String nickname;//名称
    private Date userbr;//用户生日
    private String usersex;//用户性别
    private Date createdate; //创建时间
    private String userzt; //用户状态0为正常,2为封号，0代表正常用户，1代表普通后台管理员，3代表超级管理员,4为管理员账号审核
    private String userdlzt;//0为离线，1为登录
}
