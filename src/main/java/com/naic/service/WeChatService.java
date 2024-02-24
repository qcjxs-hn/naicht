package com.naic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naic.common.Locationwachat;
import com.naic.common.WeChatSessionInfo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Service
public class WeChatService {
    private static final String APP_ID = "wxa469e7811068eb92";
    private static final String APP_SECRET = "6e9a6e687aa2a56c1665c3649a6cb665";
    private static final String WECHAT_API_URL = "https://api.weixin.qq.com/sns/jscode2session";
    private final RestTemplate restTemplate;
    public WeChatService() {
        this.restTemplate = createRestTemplate();
    }

    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // 添加 StringHttpMessageConverter 以处理 text/plain 内容类型
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        return restTemplate;
    }

    public Object code2Session(String code) {

//        System.out.println(code);
//        System.out.println(Locationwachat.appid);
        String url = WECHAT_API_URL + "?appid=" + APP_ID + "&secret=" + APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";
//        System.out.println(url);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String responseBody = response.getBody();

        // 手动将字符串转换为 WeChatSessionInfo

//        System.out.println(responseBody);
        return responseBody;
    }
    public WeChatSessionInfo convertStringToWeChatSessionInfo(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, WeChatSessionInfo.class);
        } catch (Exception e) {
            // 处理异常，比如记录日志或抛出自定义异常
            e.printStackTrace();
            return null;
        }
    }
}
