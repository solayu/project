package com.sola.sell.service.impl;

import com.sola.sell.database.ProductInfo;
import com.sola.sell.database.SellerInfo;
import com.sola.sell.dto.CartDto;
import com.sola.sell.enums.ProductStatusEnum;
import com.sola.sell.enums.ResultEnum;
import com.sola.sell.exception.SellException;
import com.sola.sell.repository.ProductInfoRepository;
import com.sola.sell.service.ProductInfoService;
import com.sola.sell.database.ProductInfo;
import com.sola.sell.repository.ProductInfoRepository;
import org.hibernate.loader.custom.ResultRowProcessor;
import org.hibernate.loader.custom.ScalarResultColumnProcessor;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findone(String productInfoId) {
        return repository.findOne(productInfoId);
    }

    @Override
    public List<ProductInfo> findUPAll() {
        return repository.findByProductStatus(0/*ProductStatusEnum.Up.getCode()*/);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDto> cartDtoList) {

    }

    @Override
    public void decreaseStock(List<CartDto> cartDtoList) {

        for(CartDto carDto:cartDtoList){
            ProductInfo productInfo=repository.findOne(carDto.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()-carDto.getProductQuantity();
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo ground(String productId) {
        ProductInfo productInfo=repository.findOne(productId);
        if(productInfo==null){ throw new SellException(ResultEnum.PRODUCT_NOT_EXIST); }
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.Up){ throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR); }
        productInfo.setProductStatus(ProductStatusEnum.Up.getCode());
        return  repository.save(productInfo);
    }

    @Override
    public ProductInfo undercarriage(String productId) {
        ProductInfo productInfo=repository.findOne(productId);
        if(productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.Down){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //修改商品状态
        productInfo.setProductStatus(ProductStatusEnum.Down.getCode());

        return repository.save(productInfo);
    }
}
