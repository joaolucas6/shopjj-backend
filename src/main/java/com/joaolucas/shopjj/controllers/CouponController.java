package com.joaolucas.shopjj.controllers;

import com.joaolucas.shopjj.models.dto.CouponDTO;
import com.joaolucas.shopjj.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupons")
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    public ResponseEntity<List<CouponDTO>> findAll(){
        return ResponseEntity.ok(couponService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(couponService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CouponDTO> create(@RequestBody CouponDTO couponDTO){
        return ResponseEntity.ok(couponService.create(couponDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponDTO> update(@PathVariable Long id, @RequestBody CouponDTO couponDTO){
        return ResponseEntity.ok(couponService.update(id, couponDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        couponService.delete(id);
        return ResponseEntity.ok().build();
    }

    

}
