package com.naic.entity;

import lombok.Data;

@Data
public class Wachatjh {
    private String code;//临时登录凭证
    private String encryptedData;//用户敏感信息的加密数据
    private String iv;//加密算法的初始向量
    private String type;//类型
}
