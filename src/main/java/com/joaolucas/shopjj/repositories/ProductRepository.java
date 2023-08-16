package com.joaolucas.shopjj.repositories;

import com.joaolucas.shopjj.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
