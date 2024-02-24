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
}
