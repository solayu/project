package com.sola.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class UrlConfig {
    //公众平台url
    public String weChatMpAuthorize;

    //开放平台url
    public  String wechatloginAuthorize;
    /*
    * 系统Url
    * */
    public String sell;
}
