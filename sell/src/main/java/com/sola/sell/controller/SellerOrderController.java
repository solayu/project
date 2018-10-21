package com.sola.sell.controller;

import com.sola.sell.dto.OrderDto;
import com.sola.sell.enums.ResultEnum;
import com.sola.sell.exception.SellException;
import com.sola.sell.service.OrderService;
import javafx.scene.control.TableView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.GET;
import javax.xml.transform.Result;
import java.io.Serializable;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/seller/order")
public class SellerOrderController implements Serializable {

    private static final long serialVersionUID = -5103567809931019064L;
    @Autowired
    private OrderService orderService;

    /*
    * 订单列表
    * */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "3") Integer size,
                             Map<String ,Object> map
                     ){

       PageRequest pageRequest=new PageRequest(page-1,size);
       Page<OrderDto> orderDtoPage=orderService.findAllList(pageRequest);
       map.put("orderDtoPage",orderDtoPage);
       map.put("page",page);
       map.put("size",size);
       return new ModelAndView("seller/list",map);
    }
    /*
    * 订单详情
    * */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String , Object> map
                               ){

        OrderDto orderDto=new OrderDto();
        try {
            orderDto = orderService.findOne(orderId);
        }catch(SellException se){
            log.error("[订单详情出错] 出错={}",se);
            map.put("msg",se.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        map.put("orderDto",orderDto);
        return new ModelAndView("seller/detail",map);
    }
    /*
    * 完成订单
    * */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                                Map<String,Object> map){
        try{
            OrderDto orderDto=orderService.findOne(orderId);
            orderService.finish(orderDto);
        }catch(SellException se){
            log.info("[卖家端完成订单] 出错={}",se);
            map.put("msg",se.getMessage());
            map.put("url","/sell/seller/order/list");
        }
        map.put("msg", ResultEnum.ORDER_FINISH_DETAIL_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }

    /*
    * 取消订单
    * */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                                Map<String,Object>   map){

        try {
            OrderDto orderDto = orderService.findOne(orderId);
            orderService.cancel(orderDto);
        }catch(SellException se){
            log.error("[卖家端取消订单] 异常点={}",se);
            map.put("msg", se.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL.getMsg());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success");
    }
}
