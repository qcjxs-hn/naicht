package com.naic.entity;

import cn.hutool.json.JSON;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@TableName("ncdd")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Ncdd {
    private String createid;//订单生成id
    private String buyuser;//购买用户
    private String buyusernickname;//购买用户昵称
    private String buyuserphone;//购买用户手机号
    private String buyuseraddress;//购买用户地址
    private String shoppingcar;//购买的奶茶
    private String sfsyyhq;//是否使用优惠券
    private String sydyhq;//使用的优惠券
    private int yfhj;//应付合计总价
    private int jsje;//减少金额
    private int paymon;//实付金额
    private Date createdate;//订单创建时间
    private String sfzf;//是否支付0为已取消，1为已支付，2为取餐中，3为订单完成
    private String sfwm;//到店还是外卖
    private String dpmc;//购买的店铺名称
    private String dpjl;//用户距离店铺多远
    private String ddbz;//用户备注
}
