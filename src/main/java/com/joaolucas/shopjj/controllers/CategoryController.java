package com.joaolucas.shopjj.controllers;

import com.joaolucas.shopjj.models.dto.CategoryDTO;
import com.joaolucas.shopjj.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.create(categoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.update(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/products/{categoryId}/{productId}")
    public ResponseEntity<Void> addProduct(@PathVariable Long categoryId, @PathVariable Long productId){
        categoryService.addProduct(categoryId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products/{categoryId}/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long categoryId, @PathVariable Long productId){
        categoryService.removeProduct(categoryId, productId);
        return ResponseEntity.ok().build();
    }

}
