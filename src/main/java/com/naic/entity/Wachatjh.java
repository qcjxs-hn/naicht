package com.naic.entity;

import lombok.Data;

@Data
public class Wachatjh {
    private String code;
    private String encryptedData;
    private String iv;
    private String type;
}
