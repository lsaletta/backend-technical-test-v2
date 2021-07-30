package com.tui.proof.mapper;

import com.tui.proof.domain.entity.OrderEntity;
import com.tui.proof.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "number", source = "id")
    @Mapping(target = "deliveryAddress", source = "address")
    @Mapping(target = "clientInformation", source = "clientInfo")
    Order toDto(OrderEntity orderEntity);
}
