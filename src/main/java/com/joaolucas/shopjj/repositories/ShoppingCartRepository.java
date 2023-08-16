package com.joaolucas.shopjj.repositories;

import com.joaolucas.shopjj.models.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
