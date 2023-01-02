package com.duck.study.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by eunduck on 2022/10/30.
 */
@Getter
@Setter
@ApiModel(value = "회원 주문 상품 정보")
public class OrderProductDto {
    private Long id;

    private Long productId;

    private String name;

    private BigDecimal orderPrice;
}
