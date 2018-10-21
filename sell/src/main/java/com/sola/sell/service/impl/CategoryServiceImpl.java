package com.sola.sell.service.impl;

import com.sola.sell.database.ProductCategory;
import com.sola.sell.repository.ProductCategoryRepository;
import com.sola.sell.service.CategoryService;
import com.sola.sell.database.ProductCategory;
import com.sola.sell.repository.ProductCategoryRepository;
import com.sola.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository repository;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory sava(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
