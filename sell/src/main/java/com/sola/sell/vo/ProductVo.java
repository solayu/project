package com.sola.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sola.sell.database.ProductInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProductVo {

    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("Type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
