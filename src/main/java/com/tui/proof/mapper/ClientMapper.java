package com.tui.proof.mapper;

import com.tui.proof.domain.entity.ClientEntity;
import com.tui.proof.model.Client;
import com.tui.proof.model.request.BaseClientRQ;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = OrderMapper.class)
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client toDto(ClientEntity clientEntity);

    ClientEntity toEntity(BaseClientRQ clientRQ);

    ClientEntity toEntity(Client client);

}
