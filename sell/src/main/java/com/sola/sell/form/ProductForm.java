package com.sola.sell.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductForm {
    Date date=new Date();
    private String productId;
    /*名字*/
    private String productName;
    /*单价*/
    private BigDecimal productPrice;
    /*库存*/
    private Integer productStock;
    /*描述*/
    private String productDescrition;
    /*小图*/
    private String productIcon;
    /*类别编号*/
    private Integer categoryType;
    /*创建时间*/
    private Date createTime=date;
    /*更新时间*/
    private Date updateTime=date;
}
