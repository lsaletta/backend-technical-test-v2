package com.tui.proof.mapper;

import com.tui.proof.domain.entity.AddressEntity;
import com.tui.proof.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toDto(AddressEntity addressEntity);
}
