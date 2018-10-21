package com.sola.sell.service;

import com.sola.sell.dto.OrderDto;

public interface BuyerService {

    //查询一个订单详情
    OrderDto findOrderDetail(String openid,String orderId);
    //取消订单
    OrderDto cancelOrder(String openid,String orderId);
}
