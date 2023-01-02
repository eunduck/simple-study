package com.duck.study.service;

import com.duck.study.converter.OrderInfoConverter;
import com.duck.study.dto.OrderInfoDto;
import com.duck.study.persistence.entity.OrderEntity;
import com.duck.study.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eunduck on 2022/11/03.
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderInfoConverter converter;

    public List<OrderInfoDto> findByUserId(Long userId) {
        List<OrderEntity> orderList = repository.findByUserId(userId);
        return converter.convert(orderList);
    }
}
