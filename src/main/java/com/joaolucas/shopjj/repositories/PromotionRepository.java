package com.joaolucas.shopjj.repositories;

import com.joaolucas.shopjj.models.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
