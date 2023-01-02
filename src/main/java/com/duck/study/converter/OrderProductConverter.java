package com.duck.study.converter;

import com.duck.study.dto.OrderProductDto;
import com.duck.study.persistence.entity.OrderProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by eunduck on 2022/10/30.
 */
@Mapper(componentModel = "spring")
public interface OrderProductConverter {
    @Mappings({
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "name", source = "product.name"),
    })
    OrderProductDto convert(OrderProductEntity entity);

    List<OrderProductDto> convert(List<OrderProductEntity> list);
}
