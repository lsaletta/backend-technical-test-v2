package com.tui.proof.domain.repository;

import com.tui.proof.domain.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, Long> {

}
