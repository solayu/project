package com.sola.sell.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum implements CodeEnum{

    Up(0,"在架"),
    Down(1,"下架"),
    OnSale(2,"特价")
    ;
    private Integer code;
    private String massage;
    ProductStatusEnum(Integer code,String massage){
        this.code=code;
        this.massage=massage;
    }

}
