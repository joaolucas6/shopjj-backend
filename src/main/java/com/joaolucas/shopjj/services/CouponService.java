package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.CouponDTO;
import com.joaolucas.shopjj.models.entities.Coupon;
import com.joaolucas.shopjj.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public List<CouponDTO> findAll(){
        return couponRepository.findAll().stream().map(CouponDTO::new).toList();
    }

    public CouponDTO findById(Long id){
        return new CouponDTO(couponRepository.findById(id).orElseThrow());
    }

    public CouponDTO create(CouponDTO couponDTO){
        Coupon coupon = new Coupon();

        coupon.setName(couponDTO.getName());
        coupon.setDescription(couponDTO.getDescription());
        coupon.setPercentage(couponDTO.getPercentage());
        coupon.setValidity(couponDTO.getValidity());

        return new CouponDTO(couponRepository.save(coupon));
    }

    public CouponDTO update(Long id, CouponDTO couponDTO){
        Coupon coupon = couponRepository.findById(id).orElseThrow();

        if(couponDTO.getName() != null) coupon.setName(couponDTO.getName());
        if(couponDTO.getDescription() != null) coupon.setDescription(couponDTO.getDescription());
        if(couponDTO.getPercentage() != null) coupon.setPercentage(couponDTO.getPercentage());
        if(couponDTO.getValidity() != null) coupon.setValidity(couponDTO.getValidity());

        return new CouponDTO(couponRepository.save(coupon));
    }

    public void delete(Long id){
        Coupon coupon = couponRepository.findById(id).orElseThrow();
        couponRepository.delete(coupon);
    }

    
}
