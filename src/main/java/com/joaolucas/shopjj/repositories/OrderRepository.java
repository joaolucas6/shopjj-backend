package com.joaolucas.shopjj.repositories;

import com.joaolucas.shopjj.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
