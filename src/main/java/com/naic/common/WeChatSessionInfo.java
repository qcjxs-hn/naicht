package com.naic.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeChatSessionInfo {
    @JsonProperty("openid")
    private String openid;
    @JsonProperty("session_key")
    private String sessionKey;

}
