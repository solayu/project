package com.sola.sell.form;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryForm {

    Date date=new Date();
    private Integer categoryId;  //类目Id
    /*类目名字*/
    private String  categoryName;
    /*类目编号*/
    private Integer categoryType;
    //创建时间
    private Date createTime=date;
    //  更新时间
    private Date updateTime=date;
}
