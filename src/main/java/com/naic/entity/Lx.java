package com.naic.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;


@Data
@TableName("lx")
public class Lx {
    private String createid; //对话id
    private String user; //用户或者客服(任何人)
    private String content;//消息主体
    private String time;//时间
    private String mesid;
}
