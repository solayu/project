package com.sola.sell.repository;

import com.sola.sell.database.SellerInfo;
import com.sola.sell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void save(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.getUniqueKey());
        sellerInfo.setSellerName("amiya");
        sellerInfo.setPassword("123456789abcde");
        sellerInfo.setOpenId("o985C1gMW9CZ7HRUJ_sNf0Newr2c");
        SellerInfo result=repository.save(sellerInfo);

        Assert.assertNotNull(result);
    }
    @Test
    public void findone(){
        SellerInfo sellerInfo=repository.findOne("1537241640581489176");
    }

    @Test
    public void findByOpenId() {
        SellerInfo result=repository.findByOpenId("123abc");
        Assert.assertEquals("123abc",result.getOpenId());
    }
}