package com.sola.sell.repository;

import com.sola.sell.database.ProductCategory;
import com.sola.sell.database.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer >{
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
