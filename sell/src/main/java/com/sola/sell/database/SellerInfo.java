package com.sola.sell.database;

import com.sola.sell.enums.LoginStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SellerInfo {
    /*
    * 卖家账号
    * */
    @Id
    private String sellerId;
    /*
    * 卖家账号名称
    * */
    private String sellerName;
    /*
    * 密码
    * */
    private String password;

    private String openId;

    private Integer loginStatus= LoginStatusEnum.Down.getCode();

}
