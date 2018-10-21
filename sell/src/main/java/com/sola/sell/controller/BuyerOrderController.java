package com.sola.sell.controller;

import com.sola.sell.converter.OrderForm2OrderDtoConverter;
import com.sola.sell.database.OrderDetail;
import com.sola.sell.dto.OrderDto;
import com.sola.sell.enums.ResultEnum;
import com.sola.sell.exception.SellException;
import com.sola.sell.form.OrderForm;
import com.sola.sell.service.BuyerService;
import com.sola.sell.service.OrderService;
import com.sola.sell.util.ResultVoUtil;
import com.sola.sell.vo.ResultVo;
import com.sola.sell.converter.OrderForm2OrderDtoConverter;
import com.sola.sell.service.BuyerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String ,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        //将
        if(bindingResult.hasErrors()){
            log.error("[创建订单]参数解析不正确,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        //判断购物车情况
        OrderDto orderDto= OrderForm2OrderDtoConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("[创建订单]购物车为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDto createResult=orderService.create(orderDto);

        Map<String, String > orderIdMap=new HashMap<>();

        orderIdMap.put("orderId",createResult.getOrderId());

        return ResultVoUtil.success(orderIdMap);
    }
    //订单列表
    @PostMapping("/list")
    public ResultVo<OrderDto> list(@RequestParam("openid") String openid,
                                    @RequestParam(value = "page",defaultValue = "0") Integer page,
                                    @RequestParam(value="size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("[查询列表]openid为空");
            throw new  SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest=new PageRequest(page,size);
        Page<OrderDto> orderDtoPage=orderService.findList(openid,pageRequest);

        return ResultVoUtil.success(orderDtoPage.getContent());
    }
    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDto> detatil(@RequestParam("openid") String openid,
                                       @RequestParam("orderid") String orderid){
        OrderDto orderDto=buyerService.findOrderDetail(openid,orderid);
       // OrderDto orderDto=orderService.findOne(orderid);
        return ResultVoUtil.success(orderDto);
    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderid") String orderid){
        /*OrderDto orderDto=orderService.findOne(orderid);
        orderService.cancel(orderDto);*/
        buyerService.cancelOrder(openid,orderid);
        return ResultVoUtil.success();
    }
}
