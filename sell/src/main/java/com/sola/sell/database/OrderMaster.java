package com.sola.sell.database;

import com.sola.sell.enums.OrderStatusEnum;
import com.sola.sell.enums.PayStatusEnum;
import com.sola.sell.enums.OrderStatusEnum;
import com.sola.sell.enums.PayStatusEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class OrderMaster {
    /*id*/
    @Id
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
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();
    /*支付状态，默认未支付*/
    private Integer payStatus= PayStatusEnum.WAIT.getCode();
    /*创建时间*/
    private Date creatTime;
    /*更新时间*/
    private Date updateTime;
  /*  @Transient
    private List<OrderDetail> orderDetailList;*/

}
