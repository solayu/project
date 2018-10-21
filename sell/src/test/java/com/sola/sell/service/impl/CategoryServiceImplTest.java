package com.sola.sell.service.impl;

import com.sola.sell.database.ProductCategory;
import com.sola.sell.repository.ProductCategoryRepository;
import com.sola.sell.database.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.stylesheets.LinkStyle;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;
    @Test
    public void findOne() {
        ProductCategory productCategory=categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategories=categoryService.findAll();
        Assert.assertNotEquals(0,productCategories.size());
    }

    @Test
    public void findByCategoryTypeIn() {
       List<ProductCategory> productCategories=categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3));
       Assert.assertNotEquals(0,productCategories.size());
    }

    @Test
    public void sava() {
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryType(2);
        productCategory.setCategoryName("素菜");
        ProductCategory result= categoryService.sava(productCategory);
        Assert.assertNotNull(result);
    }
}