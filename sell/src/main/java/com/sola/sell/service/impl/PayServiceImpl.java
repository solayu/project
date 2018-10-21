package com.sola.sell.service.impl;

import com.sola.sell.config.WechatPayConfig;
import com.sola.sell.dto.OrderDto;
import com.sola.sell.enums.ResultEnum;
import com.sola.sell.exception.SellException;
import com.sola.sell.service.OrderService;
import com.sola.sell.service.PayService;
import com.sola.sell.util.JsonUtil;
import com.sola.sell.util.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME="支付系统";
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;
    @Override
    public PayResponse create(OrderDto orderDto) {

        PayRequest payRequest=new PayRequest();
        //设置参数
        payRequest.setOrderId(orderDto.getOrderId());
        payRequest.setOpenid(orderDto.getBuyerOpenid());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOrderName(ORDER_NAME);

        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信支付] payRequest={}", JsonUtil.jsonUtil(payRequest));

        PayResponse payResponse=bestPayService.pay(payRequest);
        log.info("[微信支付] payResponse={}",JsonUtil.jsonUtil(payResponse));

        return payResponse;
    }

    @Override
    public PayResponse notify(String  notifyDa) {
        //验证签名状态
        //支付的状态
        //支付金额
        //支付人是否属于同一个人
        PayResponse payResponse=bestPayService.asyncNotify(notifyDa);
        log.info("[微信支付] payResponse={}",JsonUtil.jsonUtil(payResponse));

        //查询订单
        OrderDto orderDt=orderService.findOne(payResponse.getOrderId());
        //订单是否存在
        if(orderDt==null){
            log.info("[微信支付] 订单不存在 ordeId={}",payResponse.getOrderId() );
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //支付金额
        if(MathUtil.equals(payResponse.getOrderAmount().doubleValue(),orderDt.getOrderAmount().doubleValue())){
            log.info("[微信订单] 订单金额不一致，ordeId={},微信发送金额={}，系统给出金额={}",
                        payResponse.getOrderId(),
                        payResponse.getOrderAmount(),
                        orderDt.getOrderAmount()
                    );
            throw new SellException(ResultEnum.WXPAY_NOTIFY_VERIFY_ERROR);
        }
        //支付人是否为同一人

        orderService.paid(orderDt);
        return payResponse;
    }
/*
* 退款
* */
    @Override
    public RefundRequest refund(OrderDto orderDto) {
        RefundRequest refundRequest=new RefundRequest();
        refundRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderDto.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        return refundRequest;
    }
}
