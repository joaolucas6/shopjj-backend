package com.joaolucas.shopjj.repositories;

import com.joaolucas.shopjj.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
