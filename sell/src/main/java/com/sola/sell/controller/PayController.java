package com.sola.sell.controller;

import com.sola.sell.dto.OrderDto;
import com.sola.sell.enums.ResultEnum;
import com.sola.sell.exception.SellException;
import com.sola.sell.service.OrderService;
import com.sola.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController implements Serializable{

    private static final long serialVersionUID = -4739938837678960874L;

    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String ,Object> map
                       ){
        //查询订单
        OrderDto orderDto= orderService.findOne(orderId);
       //判断订单是否为空
        if(orderDto==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付请求
        PayResponse payResponse=payService.create(orderDto);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }
    @PostMapping("notify")
    public ModelAndView notify(@RequestBody String notify){
        payService.notify(notify);
        return new ModelAndView("pay/notifySuccess");
    }
}
