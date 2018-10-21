package com.sola.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sola.sell.database.OrderDetail;
import com.sola.sell.dto.OrderDto;
import com.sola.sell.enums.ResultEnum;
import com.sola.sell.exception.SellException;
import com.sola.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDtoConverter {

    public static OrderDto convert(OrderForm orderForm){
        Gson gson=new Gson();
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList=new ArrayList<>();
        try {
                orderDetailList= gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        }catch(Exception e){
            log.error("[Form转换为Dto]错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}
