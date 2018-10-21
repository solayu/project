package com.sola.sell.service.impl;

import com.sola.sell.database.SellerInfo;
import com.sola.sell.service.SellerInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {

    @Autowired
    private SellerInfoService service;
    @Test
    public void findSellerInfoByOpenId() {
        SellerInfo sellerInfo=service.findSellerInfoByOpenId("123abc");
        Assert.assertNotNull(sellerInfo);
    }
}