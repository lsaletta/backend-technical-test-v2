package com.tui.proof.domain.repository;

import com.tui.proof.domain.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {

    @Query("SELECT c FROM OrderEntity c WHERE (:firstName is null or c.clientInfo.firstName LIKE %:firstName%)  and (:lastName is null or c.clientInfo.lastName LIKE %:lastName%)")
    Page<OrderEntity> getByClientInfo(@Param("firstName") String firstName,
                                      @Param("lastName") String lastName, Pageable pageable);
}
