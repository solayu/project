package com.sola.sell.service.impl;

import com.sola.sell.dto.OrderDto;
import com.sola.sell.service.OrderService;
import com.sola.sell.service.PayService;
import com.sola.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    @Test
    public void create() throws Exception{

        OrderDto orderDto=orderService.findOne("1534144330248641632");
        payService.create(orderDto);

    }
}