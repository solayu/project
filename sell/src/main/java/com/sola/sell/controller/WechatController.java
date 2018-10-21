package com.sola.sell.controller;

import com.sola.sell.config.UrlConfig;
import com.sola.sell.enums.ResultEnum;
import com.sola.sell.exception.SellException;
import com.sola.sell.config.UrlConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.net.URLEncoder;

@Controller
@Slf4j
@RequestMapping("/wechat")
public class WechatController implements Serializable{
    private static final long serialVersionUID = -5531405679040736737L;
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private UrlConfig urlConfig;

    /*
    * 公众号授权
    * */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        String url=urlConfig.getWeChatMpAuthorize()+"/sell/wechat/userInfo";
        //获取重定向Url
        String redirectUrl=  wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
       // System.out.println("断点是否存在");
        log.info(redirectUrl);
        System.out.println(URLEncoder.encode(redirectUrl)+":::"+redirectUrl);

        return "redirect:"+redirectUrl;
    }
    @GetMapping("/userInfo")
    public String  userInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl ){

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken=wxMpService.oauth2getAccessToken(code);

        }catch (WxErrorException wxE){
            log.error("[微信授权]{}",wxE);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),wxE.getError().getErrorMsg());
        }
        log.error("[wxMpOAuth2AccessToken]",wxMpOAuth2AccessToken);
        String openId=wxMpOAuth2AccessToken.getOpenId();
        //System.out.println("不知道有没有获取到"+openId);
        return "redirect:"+returnUrl+"?openid="+openId;

    }
    /*
    * 平台授权（登陆授权）
    * */
    @GetMapping("/login")
    public String  login(@RequestParam("returnUrl") String returnUrl){
        String url=urlConfig.getWechatloginAuthorize()+"/sell/wechat/loginuserInfo";
        //必须要服务号，订阅号没有相关权限，因此无法给出二维码登陆界面，因此出现错误：Scope 参数错误或没有 Scope 权限
        String redirectUrl=wxMpService.buildQrConnectUrl(url,WxConsts.OAUTH2_SCOPE_USER_INFO,URLEncoder.encode(returnUrl));
        //log.info(redirectUrl);
        return "redirect:"+redirectUrl;
    }
    @GetMapping("/loginuserInfo")
    public String loginInfo(@RequestParam("code") String code,
                            @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken=wxMpService.oauth2getAccessToken(code);//code换取token
        }catch (WxErrorException we){
            log.error("[微信登陆授权]",we);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),we.getError().getErrorMsg());
        }
        log.info("wxMpOAuth2AccessToken",wxMpOAuth2AccessToken);

        String openId=wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+returnUrl+"?openid="+openId;
    }
}
