package com.tui.proof.domain.repository;

import com.tui.proof.domain.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
