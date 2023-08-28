package com.joaolucas.shopjj.services;

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
        return new ShoppingCartDTO(shoppingCartRepository.findById(id).orElseThrow());
    }

    public void addItems(Long shoppingCartId, HashMap<Long, Integer> items){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow();

        for(Map.Entry<Long, Integer> item : items.entrySet()){
            Product product = productRepository.findById(item.getKey()).orElseThrow();

            if(shoppingCart.getInventory().containsKey(product)){
                Integer oldValue = shoppingCart.getInventory().get(product);
                shoppingCart.getInventory().replace(product, oldValue, oldValue + item.getValue());
            }
            else{
                shoppingCart.getInventory().put(product, item.getValue());
            }
        }

        shoppingCartRepository.save(shoppingCart);
    }

    public void removeItems(Long shoppingCartId, HashMap<Long, Integer> items){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow();

        for(Map.Entry<Long, Integer> item : items.entrySet()) {
            Product product = productRepository.findById(item.getKey()).orElseThrow();
            shoppingCart.getInventory().remove(product);
        }

        shoppingCartRepository.save(shoppingCart);
    }


}
