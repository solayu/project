package com.sola.sell.handler;

import com.sola.sell.config.UrlConfig;
import com.sola.sell.exception.SellException;
import com.sola.sell.exception.SellerAuthorizeException;
import com.sola.sell.util.ResultVoUtil;
import com.sola.sell.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/*
* 异常捕获
* */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private UrlConfig urlConfig;

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo sellExceptionHandler(SellException se){
        return ResultVoUtil.error(se.getCode(),se.getMessage());
    }
    //登陆异常的捕获
    @ExceptionHandler(value = SellerAuthorizeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView sellerAuthorizeExceptionHandler(){
        return new ModelAndView(
            "redirect:"
                    .concat(urlConfig.getWechatloginAuthorize())
                    .concat("/sell/wechat/login")
                    .concat("?returnUrl=")
                    .concat(urlConfig.getSell())
                    .concat("/sell/seller/login")
        );
    }
}
