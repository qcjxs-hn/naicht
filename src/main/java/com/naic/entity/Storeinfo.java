package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
@Data
@TableName("storeinfo")
public class Storeinfo {
    private int id;
    private String dpmc;//店铺名称
    private String dpdyncid;//店铺对应商品记录id用;隔开
    private float latitude;//店铺纬度
    private float longitude;//店铺精度
    private String dpwz;//店铺位置
    private String createid;//店铺id
    private Date createdate;//店铺创建时间

}
