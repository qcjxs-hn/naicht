package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("swiper")
public class Swiper {
    private int id;
    private String swiperurl;//跑马灯图片链接
    private String swipercz;//跑马灯操作
    private String sfycz;//是否有操作
    private String xswz;//显示位置0为首页顶部,1为首页底部,2为我的页面顶部
    private Date createdate;//上传时间

}
