package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.CouponDTO;
import com.joaolucas.shopjj.models.entities.Coupon;
import com.joaolucas.shopjj.repositories.CouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponServiceTest {

    @Mock
    private CouponRepository couponRepository;
    private CouponService underTest;
    private Coupon coupon;

    @BeforeEach
    void setUp() {
        underTest = new CouponService(couponRepository);
        coupon = new Coupon();
    }

    @Test
    void itShouldFindAllCoupons() {
        when(couponRepository.findAll()).thenReturn(List.of(coupon));

        var result = underTest.findAll();

        assertThat(result).isEqualTo(List.of(new CouponDTO(coupon)));
    }

    @Test
    void itShouldFindCouponById() {
        when(couponRepository.findById(1L)).thenReturn(Optional.of(coupon));

        var result = underTest.findById(1L);

        assertThat(result).isEqualTo(new CouponDTO(coupon));
    }

    @Test
    void itShouldCreateCoupon() {
        when(couponRepository.save(coupon)).thenReturn(coupon);

        var result = underTest.create(new CouponDTO(coupon));

        assertThat(result).isNotNull();
    }

    @Test
    void itShouldUpdateCoupon() {
        when(couponRepository.findById(1L)).thenReturn(Optional.of(coupon));
        when(couponRepository.save(coupon)).thenReturn(coupon);

        CouponDTO toUpdate = new CouponDTO(coupon);
        toUpdate.setName("CRUZEIRO15");

        var result = underTest.update(1L, toUpdate);

        assertThat(result).isNotNull();
        assertThat(coupon.getName()).isEqualTo("CRUZEIRO15");
    }

    @Test
    void itShouldDeleteCoupon() {
        when(couponRepository.findById(1L)).thenReturn(Optional.of(coupon));

        underTest.delete(1L);

        verify(couponRepository, times(1)).delete(coupon);
    }
}