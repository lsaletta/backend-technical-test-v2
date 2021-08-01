package com.tui.proof.domain.repository;

import com.tui.proof.domain.entity.ClientEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, Long> {

}
