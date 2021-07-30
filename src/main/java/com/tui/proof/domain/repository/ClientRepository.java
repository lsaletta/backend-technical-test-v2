package com.tui.proof.domain.repository;

import com.tui.proof.domain.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, Long> {

    @Query("SELECT c FROM ClientEntity c WHERE (:firstName is null or c.firstName LIKE %:firstName%)  and (:lastName is null or c.lastName LIKE %:lastName%)")
    Page<ClientEntity> getByClientInfo(@Param("firstName") String firstName,
                                       @Param("lastName") String lastName, Pageable pageable);
}
