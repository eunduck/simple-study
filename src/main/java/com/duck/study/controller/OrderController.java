package com.duck.study.controller;

import com.duck.core.wrapper.ResultResponse;
import com.duck.study.dto.OrderInfoDto;
import com.duck.study.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by eunduck on 2022/10/28.
 */
@RestController
@Api(tags = "주문 API")
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @ApiOperation(value = "단일 회원의 주문 목록 조회")
    @GetMapping( "user/{userId}")
    public ResultResponse getOrderList(@PathVariable Long userId) {
        List<OrderInfoDto> infoDtoList = service.findByUserId(userId);

        return new ResultResponse<>(infoDtoList);
    }
}