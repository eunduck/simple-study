package com.duck.study.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by eunduck on 2022/10/30.
 */
@Getter
@Setter
@ApiModel(value = "회원 주문 정보")
public class OrderInfoDto {

    @ApiModelProperty(value = "주문번호")
    private String orderNumber;

    @ApiModelProperty(value = "주문일자")
    @JsonFormat(pattern = "yyyy-MM-dd a HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private String createdAt;

    @ApiModelProperty(value = "상품 정보")
    private List<OrderProductDto> productList;
}
