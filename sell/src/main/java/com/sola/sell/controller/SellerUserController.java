package com.sola.sell.controller;

import com.sola.sell.config.UrlConfig;
import com.sola.sell.constant.CookieConstant;
import com.sola.sell.constant.RedisConstant;
import com.sola.sell.database.SellerInfo;
import com.sola.sell.enums.ResultEnum;
import com.sola.sell.service.SellerInfoService;
import com.sola.sell.util.CookieUtil;
import com.sola.sell.config.UrlConfig;
import com.sola.sell.constant.CookieConstant;
import com.sola.sell.constant.RedisConstant;
import com.sola.sell.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/seller")
public class SellerUserController implements Serializable{
    private static final long serialVersionUID = -455070599314058291L;
    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UrlConfig urlConfig;

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openId,
                              HttpServletResponse response,
                              Map<String,Object> map){
        //与数据库匹配openid
        SellerInfo sellerInfo=sellerInfoService.findSellerInfoByOpenId(openId);
        if(sellerInfo==null){
            map.put("msg", ResultEnum.LOGIN_ERROR.getMsg());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
       /* if(sellerInfo.getLoginStatus()!=1){
            map.put("msg",ResultEnum.LOGIN_ERROR.getMsg());
            map.put("url","/sell/seller/product/list");
            return  new ModelAndView("common/error",map);
        }*/

        //设置token-redis
        String token= UUID.randomUUID().toString();
        Integer expire= RedisConstant.EXPRIT;
        //插入redis，其中第一个是生成一个key,然后将openId放入value中，设置过期时间，设置时间的格式  set
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN,token),openId,expire, TimeUnit.SECONDS);

       // token到cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);

        return new ModelAndView("redirect:"+urlConfig.sell+"/sell/seller/order/list");
    }

    @GetMapping("/loginOut")
    public ModelAndView loginOut(HttpServletRequest request,
                           HttpServletResponse response,
                           Map<String ,Object> map){
        //从cookie里查询是否有Token就是到redis里面查找
        Cookie cookie=CookieUtil.get(request,CookieConstant.TOKEN);
        if(cookie!=null){//存在
            //清除redis
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN,cookie.getValue()));
            //清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }

        map.put("msg",ResultEnum.LOGINDOUN_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");

        return new ModelAndView("common/success",map);
    }
    @GetMapping("redisTest")
    public void redisTest(){

        try {
            stringRedisTemplate.getConnectionFactory().getConnection().lInsert("list".getBytes("UTF8"),
                    RedisListCommands.Position.AFTER, "note2".getBytes("UTF8"), "after_bdb".getBytes("UTF8")
            );

        }catch (UnsupportedEncodingException ue){
            ue.printStackTrace();
        }

    }
}
