package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("wj")
public class Wj {
    private String wjtitle;//文件标题
    private String wjzw;//文件文章
    private Date createdate;//文件日期
    private String createid;//创建id

}
