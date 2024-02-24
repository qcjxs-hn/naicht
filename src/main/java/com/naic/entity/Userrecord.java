package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("userjl")
public class Userrecord {
    private int id;
    private String user;
    private String sfxggxb;//是否修改过性别
    private String sfxggbr;//是否修改过生日
    private String userjlid; //用户记录表id
}
