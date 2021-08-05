package com.tui.proof.mapper;

import com.tui.proof.domain.entity.OrderEntity;
import com.tui.proof.model.Order;
import com.tui.proof.model.request.CreateOrderRQ;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressMapper.class)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "number", source = "id")
    @Mapping(target = "deliveryAddress", source = "address")
    @Mapping(target = "clientInformation", source = "clientInfo")
    Order toDto(OrderEntity orderEntity);

    @Mapping(target = "address", source = "deliveryAddress")
    @Mapping(target = "status", expression = "java(com.tui.proof.domain.enums.EStatusType.PENDING)")
    @Mapping(target = "creationDate", expression = "java(java.time.LocalDateTime.now())")
    OrderEntity toEntity(CreateOrderRQ createOrderRQ);

}
