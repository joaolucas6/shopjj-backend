package com.joaolucas.shopjj.controllers;

import com.joaolucas.shopjj.models.dto.AddressDTO;
import com.joaolucas.shopjj.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
@RestController
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll(){
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(addressService.findById(id));
    }

    @PostMapping("/{residentId}")
    public ResponseEntity<AddressDTO> create(@PathVariable Long residentId, @RequestBody AddressDTO addressDTO){
        return ResponseEntity.ok(addressService.create(residentId, addressDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable Long id, AddressDTO addressDTO){
        return ResponseEntity.ok(addressService.update(id, addressDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }

}
