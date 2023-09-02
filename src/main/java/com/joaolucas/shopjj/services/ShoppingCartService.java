package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.exceptions.BadRequestException;
import com.joaolucas.shopjj.exceptions.ResourceNotFoundException;
import com.joaolucas.shopjj.models.dto.ShoppingCartDTO;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.ShoppingCart;
import com.joaolucas.shopjj.repositories.ProductRepository;
import com.joaolucas.shopjj.repositories.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    public List<ShoppingCartDTO> findAll(){
        return shoppingCartRepository.findAll().stream().map(ShoppingCartDTO::new).toList();
    }

    public ShoppingCartDTO findById(Long id){
        return new ShoppingCartDTO(shoppingCartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shopping cart was not found with ID: " + id)));
    }

    public void addItem(Long shoppingCartId, Long productId, Integer quantity){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow(() -> new ResourceNotFoundException("Shopping cart was not found with ID: " + shoppingCartId));

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Shopping cart was not found with ID: " + productId));

        if(shoppingCart.getInventory().containsKey(product)){
            Integer oldValue = shoppingCart.getInventory().get(product);
            shoppingCart.getInventory().replace(product, oldValue, oldValue + quantity);
        }
        else{
            shoppingCart.getInventory().put(product, quantity);
        }

        shoppingCartRepository.save(shoppingCart);
    }

    public void removeItem(Long shoppingCartId, Long productId, Integer quantity){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow(() -> new ResourceNotFoundException("Shopping cart was not found with ID: " + shoppingCartId));

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Shopping cart was not found with ID: " + productId));

        if(quantity > shoppingCart.getInventory().get(product)) throw new BadRequestException("Quantity to remove is bigger than the quantity of products at inventory");
        else if(quantity.equals(shoppingCart.getInventory().get(product))) shoppingCart.getInventory().remove(product);
        else shoppingCart.getInventory().replace(product, shoppingCart.getInventory().get(product), shoppingCart.getInventory().get(product) - quantity);

        shoppingCartRepository.save(shoppingCart);
    }


}
