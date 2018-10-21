package com.sola.sell.exception;

import com.sola.sell.enums.ResultEnum;
import com.sola.sell.enums.ResultEnum;
import lombok.Getter;

@Getter
public class SellException  extends RuntimeException{
    private Integer code;

    public SellException(ResultEnum resultEnum) {
       super(resultEnum.getMsg());
       this.code=resultEnum.getCode();
    }

    public SellException(Integer code, String msg) {
        super(msg);
        this.code = code;

    }
}
