package com.naic.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
//dec解密密码
@Component
@ConfigurationProperties(prefix = "decjm")
public class Locationfw {

    public static String jmkey;

    @Value("${decjm.jmkey}")
    public void setJmkey(String jmkey){
        Locationfw.jmkey=jmkey;
    }
}
