package com.duck.study.converter;

import com.duck.study.dto.OrderInfoDto;
import com.duck.study.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by eunduck on 2022/10/30.
 */
@Mapper(componentModel = "spring", uses = {OrderProductConverter.class})
public interface OrderInfoConverter {
    @Mappings({
            @Mapping(target = "productList", source = "productList"),
    })
    OrderInfoDto convert(OrderEntity entity);

    List<OrderInfoDto> convert(List<OrderEntity> list);
}
