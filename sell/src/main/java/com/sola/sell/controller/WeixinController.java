package com.sola.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController  implements Serializable{
    private static final long serialVersionUID = 7330773503612096722L;
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("get code的方法");
        log.info("code={}",code);

       /*// String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd898fcb01713c658&secret=29d8a650db31472aa87800e3b0d739f2&code=" + code + "&grant_type=authorization_code";
        String url="http://weixin";
        RestTemplate restTemplate=new RestTemplate();//请求得到access_token（）
        String response=restTemplate.getForObject(url,String.class);
        log.info("response={}",response);*/

       String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxa2241e13faff3e5a&secret=b0f9abc9512ad7545cc92f0ab7545fd0&code="+code+"&grant_type=authorization_code";
       RestTemplate restTemplate=new RestTemplate();
       String response=restTemplate.getForObject(url,String.class);
       log.info("response={}",response);

    }

}
