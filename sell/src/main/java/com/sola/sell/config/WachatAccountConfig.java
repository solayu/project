package com.sola.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WachatAccountConfig {

    /*
    * 开放平台的id和密钥
    * */
    private String loginAppId;
    private String loginAppSecret;

    /*
    * 公众号的id和密钥
    * */
    private String mpAppId;
    private String mpAppSecret;
    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;
    /*
    * notifyUrl
    * */
    private String notifyUrl;

    private Map<String,String> templateId;
}
