package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("ncpljl")
public class Ncpljl {
    private String ncmz;//奶茶名字
    private int ncjg;//奶茶价格
    private String ncjs;//奶茶介绍
    private String nctpurl;//奶茶图片
    private String ncpl;//奶茶品类
    private String sfzs;//奶茶是否下架
    private Date createdate;//上新时间
    private String createid;//奶茶id
}
