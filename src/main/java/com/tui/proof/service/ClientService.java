package com.tui.proof.service;

import com.tui.proof.exception.TUIMMException;
import com.tui.proof.model.Client;
import com.tui.proof.model.request.BaseClientRQ;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ClientService {

    /**
     * Get all clients.
     *
     * @param page
     * @param size
     * @return paged clients.
     */
    Page<Client> getClients(int page, int size) throws TUIMMException;

    /**
     * Save client.
     *
     * @param client
     * @return If create, return client. If not, return optional empty
     */
    Client saveClient(BaseClientRQ client) throws TUIMMException;

    /**
     * Get client by id.
     *
     * @param id
     * @return If exists, return client. If not, return optional empty
     */
    Optional<Client> getClient(Long id) throws TUIMMException;

    /**
     * Delete client by id.
     *
     * @param id
     * @throws TUIMMException
     */
    void deleteClient(Long id) throws TUIMMException;

}
