package com.naic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ncgg")
public class Ncgg {
    private String ncid;//奶茶id
    private String jl;//加料
    private String bx;//杯型
    private String wd;//温度
    private String td;//糖度
}
