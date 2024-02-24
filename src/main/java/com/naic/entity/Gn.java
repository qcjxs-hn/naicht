package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("gn")
public class Gn {
    private int id;
    private String tbdz;//功能图片
    private String title;//功能名称
    private String sfycz;//是否有操作
    private Date createdate;//创建日期
    private String createid;//创建id

}
