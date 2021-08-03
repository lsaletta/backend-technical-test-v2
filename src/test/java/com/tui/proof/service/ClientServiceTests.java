package com.tui.proof.service;


import com.tui.proof.common.BaseTest;
import com.tui.proof.domain.entity.ClientEntity;
import com.tui.proof.domain.repository.ClientRepository;
import com.tui.proof.exception.TUIMMException;
import com.tui.proof.mapper.ClientMapper;
import com.tui.proof.model.Client;
import com.tui.proof.service.impl.ClientServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTests extends BaseTest {

    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    private List<ClientEntity> clientEntityList;

    @BeforeEach
    public void init() throws IOException {
        clientEntityList =
                getFileFromListResources("jsons/Clients.json", ClientEntity.class);
        clientService = new ClientServiceImpl(clientRepository);
    }

    @Test
    void givenRepositoryOutputTest1_whenGetClients_thenReturnClient() {
        Mockito.when(clientRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(clientEntityList));

        Page<Client> clients = clientService.getClients(0, 5);
        verify(clientRepository, times(1))
                .findAll(any(Pageable.class));

        Assertions.assertEquals(4L, clients.getTotalElements());
    }

    @Test
    void givenRepositoryOutputTest2_whenGetClients_thenThrowException() {
        Mockito.when(clientRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        TUIMMException tuimmException = Assertions.assertThrows(TUIMMException.class, () -> {
            clientService.getClients(0, 5);
        });
        verify(clientRepository, times(1))
                .findAll(any(Pageable.class));

        Assert.assertEquals("No clients content", tuimmException.getErrorDescription());
    }

    @Test
    void givenRepositoryOutputTest3_whenGetClients_thenThrowException() {
        Mockito.when(clientRepository.findAll(any(Pageable.class)))
                .thenReturn(null);

        TUIMMException tuimmException = Assertions.assertThrows(TUIMMException.class, () -> {
            clientService.getClients(0, 5);
        });
        verify(clientRepository, times(1))
                .findAll(any(Pageable.class));

        Assert.assertEquals("Error in get clients", tuimmException.getErrorDescription());
    }

    @Test
    void givenRepositoryOutputTest4_whenGetClient_thenReturnClient() {
        Mockito.when(clientRepository.findById(eq(1L)))
                .thenReturn(Optional.ofNullable(clientEntityList.stream().findFirst().get()));

        Optional<Client> client = clientService.getClient(1L);

        verify(clientRepository, times(1))
                .findById(eq(1L));

        Assertions.assertTrue(client.isPresent());
        Assertions.assertEquals(1L, client.get().getId());
    }

    @Test
    void givenRepositoryOutputTest5_whenGetClient_thenReturnEmpty() {
        Mockito.when(clientRepository.findById(eq(0L)))
                .thenReturn(Optional.empty());

        Optional<Client> client = clientService.getClient(0L);

        verify(clientRepository, times(1))
                .findById(eq(0L));

        Assertions.assertFalse(client.isPresent());
    }

    @Test
    void givenRepositoryOutputTest6_whenDeleteClient_thenThrowException() {
        doThrow(new RuntimeException("test")).when(clientRepository).findById(eq(1L));

        TUIMMException tuimmException = Assertions.assertThrows(TUIMMException.class, () -> {
            clientService.getClient(1L);
        });

        verify(clientRepository, times(1))
                .findById(eq(1L));

        Assert.assertEquals("Error in get client", tuimmException.getErrorDescription());
    }

    @Test
    void givenRepositoryOutputTest7_whenDeleteClient_thenDeleteClient() {
        clientService.deleteClient(1L);
        verify(clientRepository, times(1))
                .deleteById(eq(1L));
    }

    @Test
    void givenRepositoryOutputTest8_whenDeleteClient_thenThrowException() {
        doThrow(new RuntimeException("test")).when(clientRepository).deleteById(eq(1L));

        TUIMMException tuimmException = Assertions.assertThrows(TUIMMException.class, () -> {
            clientService.deleteClient(1L);
        });

        verify(clientRepository, times(1))
                .deleteById(eq(1L));

        Assert.assertEquals("Error in delete client", tuimmException.getErrorDescription());
    }


    @Test
    void givenRepositoryOutputTest9_whenSaveClient_thenCreatedClient() {

        ClientEntity clientEntity = buildMockClientEntity();
        Mockito.when(clientRepository.save(eq(clientEntity)))
                .thenReturn(clientEntity);

        Client client = clientService.saveClient(ClientMapper.INSTANCE.toDto(clientEntity));

        verify(clientRepository, times(1))
                .save(eq(clientEntity));

        Assertions.assertEquals(clientEntity.getFirstName(), client.getFirstName());
    }


    @Test
    void givenRepositoryOutputTest10_whenSaveClient_thenCreatedClient() {

        ClientEntity clientEntity = buildMockClientEntity();

        Mockito.when(clientRepository.save(eq(clientEntity)))
                .thenThrow(new RuntimeException("test"));

        TUIMMException tuimmException = Assertions.assertThrows(TUIMMException.class, () -> {
            clientService.saveClient(ClientMapper.INSTANCE.toDto(clientEntity));
        });

        verify(clientRepository, times(1))
                .save(eq(clientEntity));

        Assert.assertEquals("Error in save client", tuimmException.getErrorDescription());
    }

    private ClientEntity buildMockClientEntity() {
        ClientEntity clientEntity = clientEntityList.stream().findFirst().get();
        clientEntity.setId(null);
        return clientEntity;
    }


}
