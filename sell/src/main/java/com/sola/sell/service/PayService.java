package com.sola.sell.service;

import com.sola.sell.dto.OrderDto;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;

public interface PayService {
    //获取订单
    PayResponse  create(OrderDto orderDto);
    //异步通知
    PayResponse notify(String  notifyDa);
    //退款
    RefundRequest refund(OrderDto orderDto);
}
