package com.sola.sell.service;

import com.sola.sell.database.ProductInfo;
import com.sola.sell.repository.ProductInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedRunService {

    public static final Integer account=100000;
    @Autowired
    private ProductInfoService infoService;
    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    private RedisTemplate redisTemplate;

    public static int alreadyRobbing=0;

    public String searchRobbingProductInfoDetail(String productId){

        ProductInfo productInfo=repository.findOne(productId);
        return "特价商品总共有"+account+"份"+"还剩余"+productInfo.getProductStock()+"已经被购买了"+alreadyRobbing;
    }

    public synchronized void redRobbingMobon(String productId){

        ProductInfo productInfo=repository.findOne(productId);
        if(productInfo==null){
            log.info("[特价商品不存在]不存在");
            return;
        }
        if(productInfo.getProductStock()<=0){
            log.info("[商品库存不够]您没有抢到");
            return;
        }

        productInfo.setProductStock(productInfo.getProductStock()-1);
        try {
            Thread.sleep(100);
        }catch (InterruptedException se){
            se.printStackTrace();
        }
        alreadyRobbing++;
        repository.save(productInfo);

    }
}
