package com.sola.sell.database.mapper;

import com.sola.sell.database.ProductCategory;
import com.sola.sell.database.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductCategorymapper {

    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertProductCategory(ProductCategory productCategory);

    @Select("select *from product_category where category_type=#{categoryType}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select *from product_category where category_name=#{categoryName}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categroyName);

    @Select("select *from product_category where category_name=#{category_name}")

    @Delete("delect *from product_category where category_type=#{category_type}")
    int delectByCategoryType(Integer categroyType);

    @Update("update product_category set category_nam=#{category_name} where category_type=#{category_type}")
    int updateByCategoryType(Integer categroyType);

    @Update("update product_category set category_name=#{category_name} where category_id=#{catgory_id}")
    int updateByCategoryId(Integer categoryId);

}
