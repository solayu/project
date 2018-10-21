package com.sola.sell.database.mapper;

import com.sola.sell.database.ProductCategory;
import com.sola.sell.database.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategorymapperTest {

    @Autowired
    private ProductCategorymapper categorymapper;

    @Test
    public void insertProductCategory() {
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("不喜欢吃的");
        productCategory.setCategoryType(10);
        int reusutl=categorymapper.insertProductCategory(productCategory);
        Assert.assertEquals(1,reusutl);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory productCategory=new ProductCategory();
        productCategory=categorymapper.findByCategoryType(4);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findByCategoryName() {
    }

    @Test
    public void delectByCategoryType() {
    }

    @Test
    public void updateByCategoryType() {
    }

    @Test
    public void updateByCategoryId() {
    }
}