package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("ncpl")
public class Ncpl {
    private int id;
    private String ncpl;//奶茶品类
    private String tit;//侧边栏名字
    private String tittp;//侧边栏图片
    private String titxz;//侧边栏名字上小字
    private Date createdate;//创建时间
    private String createid;//创建id

}
