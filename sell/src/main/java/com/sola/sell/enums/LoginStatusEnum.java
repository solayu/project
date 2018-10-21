package com.sola.sell.enums;

import lombok.Getter;

@Getter
public enum  LoginStatusEnum implements CodeEnum {
    Up(0,"登陆"),
    Down(1,"登出")
    ;
    private Integer code;
    private String massage;
    LoginStatusEnum(Integer code,String massage){
        this.code=code;
        this.massage=massage;
    }

}
