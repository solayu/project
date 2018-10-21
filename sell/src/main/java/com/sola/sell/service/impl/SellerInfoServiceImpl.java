package com.sola.sell.service.impl;

import com.sola.sell.database.SellerInfo;
import com.sola.sell.repository.SellerInfoRepository;
import com.sola.sell.service.SellerInfoService;
import com.sola.sell.repository.SellerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenId(String openId) {

        return repository.findByOpenId(openId);
    }
}
