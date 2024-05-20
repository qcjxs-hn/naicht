package com.naic.common;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "qnylink")

public class Locationzy {

    public static String qnhostname;

    public static String akey;

    public static String skey;

    public static String bu;

    @Value("${qnylink.qnhostname}")
    public void setQnhostname(String qnhostname) {
        Locationzy.qnhostname = qnhostname;
    }

    @Value("${qnylink.akey}")
    public void setAkey(String akey) {
        Locationzy.akey = akey;
    }
    @Value("${qnylink.skey}")
    public  void setSkey(String skey) {
        Locationzy.skey = skey;
    }
    @Value("${qnylink.bu}")
    public void setBu(String bu) {
        Locationzy.bu = bu;
    }
}
