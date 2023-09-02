package com.joaolucas.shopjj.controllers;

import com.joaolucas.shopjj.models.dto.PromotionDTO;
import com.joaolucas.shopjj.services.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping
    public ResponseEntity<List<PromotionDTO>> findAll(){
        return ResponseEntity.ok(promotionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(promotionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PromotionDTO> create(@RequestBody PromotionDTO promotionDTO){
        return ResponseEntity.ok(promotionService.create(promotionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromotionDTO> update(@PathVariable Long id, @RequestBody PromotionDTO promotionDTO){
        return ResponseEntity.ok(promotionService.update(id, promotionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        promotionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/products/{promotionId}/{productId}")
    public ResponseEntity<Void> addItem(@PathVariable Long promotionId, @PathVariable Long productId){
        promotionService.addProduct(promotionId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products/{promotionId}/{productId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long promotionId, @PathVariable Long productId){
        promotionService.removeProduct(promotionId, productId);
        return ResponseEntity.ok().build();
    }

}
