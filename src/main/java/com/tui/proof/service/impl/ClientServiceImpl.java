package com.tui.proof.service.impl;

import com.tui.proof.domain.repository.ClientRepository;
import com.tui.proof.exception.TUIMMException;
import com.tui.proof.model.Client;
import com.tui.proof.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Log4j2
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;


    @Override
    public Page<Client> getClients(int page, int size) throws TUIMMException {
        return null;
    }

    @Override
    public Client saveClient(Client client) throws TUIMMException {
        return null;
    }

    @Override
    public Optional<Client> getClient(Long id) throws TUIMMException {
        return Optional.empty();
    }

    @Override
    public void deleteClient(Long id) throws TUIMMException {

    }
}
