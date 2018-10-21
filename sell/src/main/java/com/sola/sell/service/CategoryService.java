package com.sola.sell.service;

import com.sola.sell.database.ProductCategory;
import com.sola.sell.database.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory sava(ProductCategory productCategory);
}
