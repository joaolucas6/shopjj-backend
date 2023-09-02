package com.joaolucas.shopjj.controllers;

import com.joaolucas.shopjj.models.dto.OrderDTO;
import com.joaolucas.shopjj.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/orders")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping("/{costumerId}")
    public ResponseEntity<OrderDTO> create(@PathVariable Long costumerId, @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.create(costumerId, orderDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable Long id, @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.update(id, orderDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }


}
