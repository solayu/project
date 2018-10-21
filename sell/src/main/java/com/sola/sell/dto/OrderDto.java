package com.sola.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sola.sell.database.OrderDetail;
import com.sola.sell.enums.OrderStatusEnum;
import com.sola.sell.enums.PayStatusEnum;
import com.sola.sell.util.EnumUtil;
import com.sola.sell.util.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    Date date=new Date();
    /*id*/
    private String orderId;
    /*购买着名*/
    private String buyerName;
    /*购买者电话*/
    private String buyerPhone;
    /*买家微信opendid*/
    private String buyerOpenid;
    /*买家地址*/
    private String buyerAddress;
    /*购买价格*/
    private BigDecimal orderAmount;
    /*购买状态，默认0个新订单*/
    private Integer orderStatus;
    /*支付状态，默认未支付*/
    private Integer payStatus;
    /*创建时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date creatTime=date;
    /*更新时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime=date;

    List<OrderDetail> orderDetailList;

    public OrderStatusEnum getOrderStatusEnum(){

        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){

        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
