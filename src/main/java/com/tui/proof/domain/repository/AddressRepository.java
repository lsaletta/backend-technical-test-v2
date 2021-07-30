package com.tui.proof.domain.repository;

import com.tui.proof.domain.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}
