package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("indexqyl")
public class Qyl {
    private int id;
    private String qylwz;
    private String qyltb;
    private String qylcz;
    private String qylsfycz;
    private Date createdate;
}
