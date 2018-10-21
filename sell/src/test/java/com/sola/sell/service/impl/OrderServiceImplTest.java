package com.sola.sell.service.impl;

import com.sola.sell.database.OrderDetail;
import com.sola.sell.dto.OrderDto;
import com.sola.sell.enums.OrderStatusEnum;
import com.sola.sell.enums.PayStatusEnum;
import com.sola.sell.service.OrderService;
import com.sola.sell.enums.OrderStatusEnum;
import com.sola.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID="11023";

    private final String ORDER_ID="1534145637814514127";
    @Test
    public void create() {
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerName("小子");
        orderDto.setBuyerAddress("浙江");
        orderDto.setBuyerPhone("1322334541111");
        orderDto.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList=new ArrayList<>();

        OrderDetail o1=new OrderDetail();
        o1.setProductId("15");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);

        OrderDetail o2=new OrderDetail();
        o2.setProductId("13");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);

        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result=orderService.create(orderDto);
        log.info("创建订单：result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDto result=orderService.findOne(ORDER_ID);
        log.info("查找订单：result{}",result);
       // Assert.assertNotEquals(ORDER_ID,result.getOrderId());

    }

    @Test
    public void findList() {
        PageRequest request=new PageRequest(1,2);
        Page<OrderDto> orderDtoPage=orderService.findList(BUYER_OPENID,request);
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDto orderDto=orderService.findOne(ORDER_ID);
        OrderDto result= orderService.cancel(orderDto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());

    }

    @Test
    public void finish() {
        OrderDto orderDto=orderService.findOne("1534145194771741741");
        OrderDto result=orderService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDto orderDto=orderService.findOne("1534144330248641632");
        OrderDto result=orderService.paid(orderDto);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());

    }

    @Test
    public void findAllpage(){
        PageRequest pageRequest=new PageRequest(1,10);
        Page<OrderDto> orderDtoPage=orderService.findAllList(pageRequest);
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());

    }
}