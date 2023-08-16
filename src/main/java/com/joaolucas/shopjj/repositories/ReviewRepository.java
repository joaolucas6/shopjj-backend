package com.joaolucas.shopjj.repositories;

import com.joaolucas.shopjj.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
