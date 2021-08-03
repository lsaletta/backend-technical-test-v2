package com.tui.proof.service.impl;

import com.tui.proof.domain.entity.ClientEntity;
import com.tui.proof.domain.repository.ClientRepository;
import com.tui.proof.exception.TUIMMException;
import com.tui.proof.mapper.ClientMapper;
import com.tui.proof.model.Client;
import com.tui.proof.model.request.BaseClientRQ;
import com.tui.proof.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Log4j2
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Page<Client> getClients(final int page, final int size) throws TUIMMException {

        try {
            Page<ClientEntity> allResults = clientRepository.findAll(PageRequest.of(page, size));
            if (allResults.getTotalElements() != 0L) {
                return allResults.map(item -> ClientMapper.INSTANCE.toDto(item));
            }
        } catch (Exception e) {
            log.error("Error in get clients: {}", e);
            throw new TUIMMException("Error in get clients", e);
        }

        throw new TUIMMException("No clients content");

    }

    @Override
    public Client saveClient(final BaseClientRQ client) throws TUIMMException {
        try {
            ClientEntity clientEntity = ClientMapper.INSTANCE.toEntity(client);
            return ClientMapper.INSTANCE.toDto(clientRepository.save(clientEntity));
        } catch (Exception e) {
            log.error("Error in save client: {}", e);
            throw new TUIMMException("Error in save client", e);
        }
    }

    @Override
    public Optional<Client> getClient(final Long id) throws TUIMMException {
        try {
            return clientRepository.findById(id).map(item -> ClientMapper.INSTANCE.toDto(item));
        } catch (Exception e) {
            log.error("Error in get client: {}", e);
            throw new TUIMMException("Error in get client", e);
        }
    }

    @Override
    public void deleteClient(final Long id) throws TUIMMException {
        try {
            clientRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error in delete client: {}", e);
            throw new TUIMMException("Error in delete client", e);
        }
    }
}
