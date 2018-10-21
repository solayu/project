package com.sola.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WchatOpenConfig {

    @Autowired
    private WachatAccountConfig wachatAccountConfig;

    @Bean
    public WxMpService wxOpenService(){

       WxMpService wxMpService=new WxMpServiceImpl();
       wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
       return wxMpService;
    }
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage=new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(wachatAccountConfig.getLoginAppId());
        wxMpInMemoryConfigStorage.setSecret(wachatAccountConfig.getLoginAppSecret());
        return wxMpInMemoryConfigStorage;
    }
}
