package com.joaolucas.shopjj.repositories;

import com.joaolucas.shopjj.models.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
