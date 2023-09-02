package com.joaolucas.shopjj.controllers;

import com.joaolucas.shopjj.models.dto.ShoppingCartDTO;
import com.joaolucas.shopjj.services.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/shopping-carts")
@RequiredArgsConstructor
@RestController
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public ResponseEntity<List<ShoppingCartDTO>> findAll(){
        return ResponseEntity.ok(shoppingCartService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(shoppingCartService.findById(id));
    }

    @PostMapping("/items/{shoppingCartId}/{productId}/{quantity}")
    public ResponseEntity<Void> addItem(@PathVariable Long shoppingCartId, @PathVariable Long productId, @PathVariable Integer quantity){
        shoppingCartService.addItem(shoppingCartId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/items/{shoppingCartId}/{productId}/{quantity}")
    public ResponseEntity<Void> removeItem(@PathVariable Long shoppingCartId, @PathVariable Long productId, @PathVariable Integer quantity){
        shoppingCartService.removeItem(shoppingCartId, productId, quantity);
        return ResponseEntity.ok().build();
    }
}
