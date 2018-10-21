package com.sola.sell.controller;

import com.sola.sell.database.ProductCategory;
import com.sola.sell.database.ProductInfo;
import com.sola.sell.service.CategoryService;
import com.sola.sell.service.ProductInfoService;
import com.sola.sell.util.ResultVoUtil;
import com.sola.sell.vo.ProductInfoVo;
import com.sola.sell.vo.ProductVo;
import com.sola.sell.vo.ResultVo;
import com.sola.sell.database.ProductCategory;
import com.sola.sell.database.ProductInfo;
import com.sola.sell.service.CategoryService;
import com.sola.sell.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductCategoryController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo list(){
        //查询所有的上架商品
        List<ProductInfo> productInfos=productInfoService.findUPAll();
        //查询类目
        List<Integer> categoryTypeList=new ArrayList<>();

        //传统
        for (ProductInfo productInfo: productInfos) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
       // List<Integer> categoryTypeList=productInfos.stream().map(e->e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTypeList);
        List<ProductVo> productVoList=new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList) {
            ProductVo productVo=new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVos=new ArrayList<>();
            for (ProductInfo productInfo: productInfos) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo=new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVos.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVos);
            productVoList.add(productVo);
        }

        return ResultVoUtil.success(productVoList);
    }

}
