package com.tui.proof.ws.controller;

import com.tui.proof.model.Client;
import com.tui.proof.model.request.BaseClientRQ;
import com.tui.proof.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    /**
     * Get all clients.
     *
     * @param page
     * @param size
     * @return paged clients.
     */
    @GetMapping("")
    public ResponseEntity<Page<Client>> getAll(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size) {
        return new ResponseEntity<>(clientService.getClients(page, size), HttpStatus.OK);
    }

    /**
     * Save client.
     *
     * @param client
     * @return If create client, return CREATED httpStatus.
     * If not create client, return OK httpStatus.
     */
    @PostMapping("")
    public ResponseEntity<Client> save(
            @Valid @RequestBody final BaseClientRQ client) {
        return new ResponseEntity<>(clientService.saveClient(client), HttpStatus.CREATED);
    }

    /**
     * Get client by id.
     *
     * @param id
     * @return If exists, return client. If not, return response with noContent httpStatus.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable(value = "id") final Long id) {
        Optional<Client> result = clientService.getClient(id);
        return result.isPresent() ? new ResponseEntity<>(result.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Delete client by id.
     *
     * @param id
     * @return If deleted, return client. If not, return response with noContent httpStatus.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") final Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
