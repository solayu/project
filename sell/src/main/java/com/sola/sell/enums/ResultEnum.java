package com.sola.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    CART_EMPTY(1,"购物车不存在"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STCK_ERROR(11,"商品库存无"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态出错"),
    ORDER_UPDATE_FAIL (15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情不存在"),
    WXPAY_NOTIFY_VERIFY_ERROR(17,"微信给出的金额和系统金额不匹配"),
    ORDER_PAY_STATUS(18,"订单支付失败"),
    PARAM_ERROR(19,"参数不正确"),
    ORDER_OWNER_ERROR(20,"订单不是自己的"),
    WECHAT_MP_ERROR(21,"网页授权失败"),
    ORDER_CANCEL(22,"订单取消成功"),
    ORDER_FINISH_DETAIL_SUCCESS(23,"查询订单完结详情"),
    PRODUCT_STATUS_ERROR(24,"商品状态出错"),
    LOGIN_ERROR(25,"登陆失败"),
    LOGIN_SUCCESS(26,"登陆成功"),
    LOGINDOUN_SUCCESS(27,"登出成功"),
    LOGINDOWN_ERROE(28,"登出失败"),
    INFO_ERROR(29,"商品详情查询失败")
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
