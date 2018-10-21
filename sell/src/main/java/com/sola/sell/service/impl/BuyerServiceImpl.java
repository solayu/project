package com.sola.sell.service.impl;

import com.sola.sell.dto.OrderDto;
import com.sola.sell.enums.ResultEnum;
import com.sola.sell.exception.SellException;
import com.sola.sell.service.BuyerService;
import com.sola.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;
    @Override
    public OrderDto findOrderDetail(String openid, String orderid) {
        return checkOrderO(openid,orderid);
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderid) {
        OrderDto orderDto=checkOrderO(openid,orderid);
        if(orderDto==null){
            log.error("[取消订单]查找不到订单,orderid={}",orderid);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDto);
    }

    private OrderDto checkOrderO(String openid,String orderid){

        OrderDto orderDto=orderService.findOne(orderid);
        if(orderDto==null){
            return null;
        }
        if(!orderDto.getBuyerOpenid().equals(openid)){
            log.error("[查询订单]失败，openid={},order={}",openid,orderid);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
