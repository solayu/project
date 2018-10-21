package com.sola.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatPayConfig {
    @Autowired
    private WachatAccountConfig wachatAccountConfig;
   @Bean
    public BestPayServiceImpl bestPayService(){
       WxPayH5Config wxPayH5Config=new WxPayH5Config();
       wxPayH5Config.setAppId(wachatAccountConfig.getMpAppId());
       wxPayH5Config.setAppSecret(wachatAccountConfig.getMpAppSecret());
       wxPayH5Config.setMchId(wachatAccountConfig.getMchId());
       wxPayH5Config.setMchKey(wachatAccountConfig.getMchKey());
       wxPayH5Config.setKeyPath(wachatAccountConfig.getKeyPath());
       wxPayH5Config.setNotifyUrl(wachatAccountConfig.getNotifyUrl());
       BestPayServiceImpl bestPayService=new BestPayServiceImpl();
       bestPayService.setWxPayH5Config(wxPayH5Config);
       return bestPayService;
   }

 /*  @Bean
    public WxPayH5Config wxPayH5Config(){

       WxPayH5Config wxPayH5Config=new WxPayH5Config();
       wxPayH5Config.setAppId(wachatAccountConfig.getMpAppId());
       wxPayH5Config.setAppSecret(wachatAccountConfig.getMpAppSecret());
       wxPayH5Config.setMchId(wachatAccountConfig.getMchId());
       wxPayH5Config.setMchKey(wachatAccountConfig.getMchKey());
       wxPayH5Config.setKeyPath(wachatAccountConfig.getKeyPath());
       return wxPayH5Config;
   }*/
}
