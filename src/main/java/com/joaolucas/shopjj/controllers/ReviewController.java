package com.joaolucas.shopjj.controllers;

import com.joaolucas.shopjj.models.dto.ReviewDTO;
import com.joaolucas.shopjj.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> findAll(){
        return ResponseEntity.ok(reviewService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @PostMapping("/{authorId}/{productId}")
    public ResponseEntity<ReviewDTO> create(@PathVariable Long authorId, @PathVariable Long productId, @RequestBody ReviewDTO reviewDTO){
        return ResponseEntity.ok(reviewService.create(authorId, productId, reviewDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> update(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO){
        return ResponseEntity.ok(reviewService.update(id, reviewDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        reviewService.delete(id);
        return ResponseEntity.ok().build();
    }
}
