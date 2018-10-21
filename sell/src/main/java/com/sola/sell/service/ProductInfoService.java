package com.sola.sell.service;

import com.sola.sell.database.ProductInfo;
import com.sola.sell.dto.CartDto;
import com.sola.sell.database.ProductInfo;
import com.sun.javafx.geom.transform.CanTransformVec3d;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
    ProductInfo findone(String productInfoId);
    List<ProductInfo> findUPAll();
    Page<ProductInfo> findAll(Pageable pageable);//分页查询
    ProductInfo save(ProductInfo productInfo);
    //加库存
    void increaseStock(List<CartDto> cartDtoList);
    //减库存
    void decreaseStock(List<CartDto> cartDtoList);
    //上架
    ProductInfo ground(String productId);
    //下架
    ProductInfo undercarriage(String productId);
}
