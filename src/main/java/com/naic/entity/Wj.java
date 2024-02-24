package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("wj")
public class Wj {
    private String wjtitle;
    private String wjzw;
    private Date createdate;
    private String createid;

}
