package com.naic.entity;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class Buytea {
    @JSONField(name = "name")
    private String name;

    @JSONField(name = "nctpurl")
    private String nctpurl;

    @JSONField(name = "bx")
    private String bx;

    @JSONField(name = "wd")
    private String wd;

    @JSONField(name = "td")
    private String td;

    @JSONField(name = "ncjg")
    private int ncjg;

    @JSONField(name = "bjz")
    private int bjz;

    @JSONField(name = "jl")
    private List<String> jl;
}
