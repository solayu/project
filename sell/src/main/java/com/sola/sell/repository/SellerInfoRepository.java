package com.sola.sell.repository;

import com.sola.sell.database.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String>{

    SellerInfo findByOpenId(String openId);
}
