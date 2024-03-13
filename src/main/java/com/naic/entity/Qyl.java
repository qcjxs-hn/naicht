package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("indexqyl")
public class Qyl {
    private int id;
    private String qylwz;//权益栏文字
    private String qyltb;//权益栏图标
    private String qylcz;//权益栏操作
    private String qylsfycz;//权益栏是否有操作
    private Date createdate;//时间
}
