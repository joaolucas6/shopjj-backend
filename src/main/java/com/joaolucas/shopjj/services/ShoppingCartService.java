package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.ShoppingCartDTO;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.ShoppingCart;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.repositories.ProductRepository;
import com.joaolucas.shopjj.repositories.ShoppingCartRepository;
import com.joaolucas.shopjj.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<ShoppingCartDTO> findAll(){
        return shoppingCartRepository.findAll().stream().map(ShoppingCartDTO::new).toList();
    }

    public ShoppingCartDTO findById(Long id){
        return new ShoppingCartDTO(shoppingCartRepository.findById(id).orElseThrow());
    }

    public void addItem(Long userId, Long productId, Integer quantity){
        Product product = productRepository.findById(productId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        ShoppingCart shoppingCart = user.getShoppingCart();

        if (shoppingCart.getInventory().containsKey(product)){
            Integer oldValue = shoppingCart.getInventory().get(product);
            shoppingCart.getInventory().replace(product, oldValue, oldValue + quantity);
        }
        else{
            shoppingCart.getInventory().put(product, quantity);
        }
    }

    public void removeItem(Long userId, Long productId, Integer quantity){
        Product product = productRepository.findById(productId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        ShoppingCart shoppingCart = user.getShoppingCart();

        if(!shoppingCart.getInventory().containsKey(product)) throw new RuntimeException();

        Integer oldQuantity = shoppingCart.getInventory().get(product);

        if(shoppingCart.getInventory().get(product) <= quantity) {
            shoppingCart.getInventory().remove(product);
        }
        else{
            shoppingCart.getInventory().replace(product, oldQuantity, oldQuantity - quantity);
        }
    }
}
