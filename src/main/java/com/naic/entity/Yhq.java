package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("yhq")
public class Yhq {
    private String  qid;//优惠券id
    private String  yhqtb;//优惠券图标
    private int yhqje;//优惠券金额
    private Date createdate;//优惠券创建时间
    private int yxq;//优惠券有效期
    private String yhqsfsy;//是否使用
    private String yhqqy;//是否弃用

}
