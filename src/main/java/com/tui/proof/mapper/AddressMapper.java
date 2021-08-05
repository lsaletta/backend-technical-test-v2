package com.tui.proof.mapper;

import com.tui.proof.domain.entity.AddressEntity;
import com.tui.proof.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "postcode", source = "postCode")
    Address toDto(AddressEntity addressEntity);

    @Mapping(target = "postCode", source = "postcode")
    AddressEntity toEntity(Address address);

    @Mapping(target = "postCode", source = "address.postcode")
    @Mapping(target = "id", source = "id")
    AddressEntity toEntity(Long id, Address address);
}
