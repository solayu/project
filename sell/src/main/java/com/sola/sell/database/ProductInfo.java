package com.sola.sell.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sola.sell.enums.ProductStatusEnum;
import com.sola.sell.util.EnumUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class ProductInfo {

    @Id
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
    private Date createTime= new Date();
    /*更新时间*/
    private Date updateTime= new Date();
    /*状态*/
    private Integer productStatus= ProductStatusEnum.Up.getCode();
    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescrition, String productIcon, Integer categoryType, Date createTime, Date updateTime,Integer productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescrition = productDescrition;
        this.productIcon = productIcon;
        this.categoryType = categoryType;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.productStatus=productStatus;
    }

    public ProductInfo() {
    }
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}
