package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("dpuserzy")
public class Dpuserdy {
    private String user;//店铺的对应管理员
    private String userphone;//店铺的对应管理员对话
    private String dpmc;//对应店铺名称
    private float latitude;//店铺纬度
    private float longitude;//店铺精度
    private String dpwz;//对应店铺位置
    private String createid;//创建id
    private Date createdate;//时间
    private String dpzt;//0为待审核，1为开店状态，2为店铺已注销

}
