package com.joaolucas.shopjj.repositories;

import com.joaolucas.shopjj.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
