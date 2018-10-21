package com.sola.sell.controller;

import com.sola.sell.database.ProductCategory;
import com.sola.sell.database.ProductInfo;
import com.sola.sell.exception.SellException;
import com.sola.sell.form.CategoryForm;
import com.sola.sell.service.CategoryService;
import com.sola.sell.service.OrderService;
import com.lly835.bestpay.rest.type.Get;
import com.sola.sell.database.ProductCategory;
import com.sola.sell.form.CategoryForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/seller/category")
public class SellerCategoryController implements Serializable{

    private static final long serialVersionUID = -1713505841632153890L;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String ,Object> map){

        List<ProductCategory> productCategories=categoryService.findAll();
        map.put("productCategories",productCategories);

        return new ModelAndView("category/list",map);
    }
    @GetMapping("/revise")
    public ModelAndView revise(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                                Map<String ,Object> map
        ){

        if(!StringUtils.isEmpty(categoryId)){
            ProductCategory productCategory=categoryService.findOne(categoryId);
            map.put("productCategory",productCategory);
        }
        
        return new ModelAndView("category/revise",map);
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String ,Object> map
                             ){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/revise");
            return  new ModelAndView("common/error",map);
        }
        ProductCategory productCategory=new ProductCategory();
        try{
            if(!StringUtils.isEmpty(categoryForm.getCategoryId())){
                productCategory=categoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm,productCategory);
            categoryService.sava(productCategory);
        }catch (SellException se){
            map.put("msg",se.getMessage());
            map.put("url","/sell/seller/category/revise");
        }
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }
}
