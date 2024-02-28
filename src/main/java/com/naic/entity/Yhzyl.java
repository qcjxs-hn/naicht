package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("yhzyl")
public class Yhzyl {
    //用户资源栏
    private String createid;
    private String bt;//资源个数
    private String sm;//资源栏名字
    private String tp;//资源栏图片
    private Date createdate;//创建时间
}
