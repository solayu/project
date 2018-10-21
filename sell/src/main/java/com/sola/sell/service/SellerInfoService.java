package com.sola.sell.service;

import com.sola.sell.database.SellerInfo;

public interface SellerInfoService {

    SellerInfo findSellerInfoByOpenId(String openId);
}
