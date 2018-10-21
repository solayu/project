package com.sola.sell.controller;

import com.sola.sell.database.ProductCategory;
import com.sola.sell.database.ProductInfo;
import com.sola.sell.dto.OrderDto;
import com.sola.sell.enums.ProductStatusEnum;
import com.sola.sell.enums.ResultEnum;
import com.sola.sell.exception.SellException;
import com.sola.sell.form.ProductForm;
import com.sola.sell.service.CategoryService;
import com.sola.sell.service.ProductInfoService;
import com.sola.sell.util.KeyUtil;
import com.sola.sell.database.ProductCategory;
import javafx.beans.binding.ObjectExpression;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.*;
import javax.validation.Valid;
import javax.ws.rs.GET;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value="size",defaultValue = "1") Integer size,
                             Map<String ,Object> map) {

        PageRequest pageRequest=new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage =productInfoService.findAll(pageRequest);
        map.put("productInfoPage",productInfoPage);
        map.put("page",page);
        map.put("size",size);
        return new ModelAndView("product/list");
    }
    @GetMapping("/ground")
    public ModelAndView ground(@RequestParam("productId") String productId,
                               Map<String ,Object> map
                               ){
       // ProductInfo productInfo=productInfoService.findone(productId);
       try {
           productInfoService.ground(productId);
       }catch(SellException se){
           map.put("msg",se.getMessage());
           map.put("url", "/sell/seller/product/list");
           return new ModelAndView("common/error",map);
       }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/undercarriage")
    public ModelAndView undercarriage(@RequestParam(value = "productId") String productId,
                               Map<String ,Object> map){
        try {
            productInfoService.undercarriage(productId);
        }catch(SellException se){
            map.put("msg",se.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/revise")
    public ModelAndView revise(@RequestParam(value = "productId",required = false) String productId,
                               Map<String ,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo=productInfoService.findone(productId);
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> productCategoryList=categoryService.findAll();
        map.put("categoryList",productCategoryList);
        return  new ModelAndView("product/revise",map);
    }

    @PostMapping("save")
    public ModelAndView save(@Valid ProductForm productForm,
                      BindingResult bindingResult,
                      Map<String,Object> map
                     ){
         if(bindingResult.hasErrors()){
             map.put("msg",bindingResult.getFieldError().getDefaultMessage());
             map.put("url", "/sell/seller/product/revise");
             return new ModelAndView("common/error",map);
         }
         ProductInfo productInfo=new ProductInfo();

       try{
           //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(productForm.getProductId())) {
                productInfo = productInfoService.findone(productForm.getProductId());
            } else {
                productForm.setProductId(KeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(productForm,productInfo);
            productInfoService.save(productInfo);
          }catch(SellException se){
            map.put("msg",se.getMessage());
            map.put("url", "/sell/seller/product/revise");
            return new ModelAndView("common/error",map);
          }

        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

}
