package com.naic.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechatapilink")
@Data
public class Locationwachat {
    public static String appid;
    public static String appsecret;
    public static String wecahtapiurl;


    @Value("${wechatapilink.appid}")
    public static void setAppid(String appid) {
        Locationwachat.appid = appid;
    }


    @Value("${wechatapilink.appsecret}")
    public static void setAppsecret(String appsecret) {
        Locationwachat.appsecret = appsecret;
    }

    @Value("${wechatapilink.wechatapiurl}")
    public static void setWecahtapiurl(String wecahtapiurl) {
        Locationwachat.wecahtapiurl = wecahtapiurl;
    }
}
