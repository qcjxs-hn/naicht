package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("adminloginhcc")
public class AdminLoginhcc {
    private String code;
    private String user;
}
