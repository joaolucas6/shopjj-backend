package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.controllers.CouponController;
import com.joaolucas.shopjj.controllers.OrderController;
import com.joaolucas.shopjj.exceptions.BadRequestException;
import com.joaolucas.shopjj.exceptions.ResourceNotFoundException;
import com.joaolucas.shopjj.models.dto.CouponDTO;
import com.joaolucas.shopjj.models.entities.Coupon;
import com.joaolucas.shopjj.repositories.CouponRepository;
import com.joaolucas.shopjj.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public List<CouponDTO> findAll(){
        return couponRepository.findAll().stream().map(coupon -> new CouponDTO(coupon).add(linkTo(methodOn(CouponController.class).findById(coupon.getId())).withSelfRel())).toList();
    }

    public CouponDTO findById(Long id){
        return new CouponDTO(couponRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Coupon was not found with ID: "))).add(linkTo(methodOn(CouponController.class).findById(id)).withSelfRel());
    }

    public CouponDTO create(CouponDTO couponDTO){
        if(!DataValidation.isCouponInfoValid(couponDTO)) throw new BadRequestException("Coupon info is invalid!");

        Coupon coupon = new Coupon();

        coupon.setName(couponDTO.getName());
        coupon.setDescription(couponDTO.getDescription());
        coupon.setPercentage(couponDTO.getPercentage());
        coupon.setValidity(couponDTO.getValidity());

        Coupon savedCoupon = couponRepository.save(coupon);

        return new CouponDTO().add(linkTo(methodOn(CouponController.class).findById(savedCoupon.getId())).withSelfRel());
    }

    public CouponDTO update(Long id, CouponDTO couponDTO){
        if(!DataValidation.isCouponInfoValid(couponDTO)) throw new BadRequestException("Coupon info is invalid!");


        Coupon coupon = couponRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Coupon was not found with ID: "));

        if(couponDTO.getName() != null) coupon.setName(couponDTO.getName());
        if(couponDTO.getDescription() != null) coupon.setDescription(couponDTO.getDescription());
        if(couponDTO.getPercentage() != null) coupon.setPercentage(couponDTO.getPercentage());
        if(couponDTO.getValidity() != null) coupon.setValidity(couponDTO.getValidity());

        Coupon savedCoupon = couponRepository.save(coupon);

        return new CouponDTO().add(linkTo(methodOn(CouponController.class).findById(savedCoupon.getId())).withSelfRel());
    }

    public void delete(Long id){
        Coupon coupon = couponRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Coupon was not found with ID: "));
        couponRepository.delete(coupon);
    }


}
