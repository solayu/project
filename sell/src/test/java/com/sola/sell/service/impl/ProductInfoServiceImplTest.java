package com.sola.sell.service.impl;

import com.sola.sell.database.ProductInfo;
import com.sola.sell.service.ProductInfoService;
import com.sola.sell.database.ProductInfo;
import com.sola.sell.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService service;
    @Test
    public void findone() {
        ProductInfo result=service.findone("12");
        Assert.assertNotNull(result);
    }

    @Test
    public void findUPAll() {
        List<ProductInfo> productInfos=service.findUPAll();
        Assert.assertNotEquals(0,productInfos.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest=new PageRequest(0,2);
        Page<ProductInfo> productInfoPage=service.findAll(pageRequest);
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("15");
        productInfo.setProductName("蔬菜叶");
        productInfo.setProductPrice(new BigDecimal(5.2));
        productInfo.setProductDescrition("很好吃，是不可能给你留的");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(1);
        productInfo.setProductStock(100);
        service.save(productInfo);
    }
}