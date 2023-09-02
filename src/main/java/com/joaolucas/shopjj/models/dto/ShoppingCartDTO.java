package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.ShoppingCart;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartDTO extends RepresentationModel<ShoppingCartDTO> {

    private Long id;
    private Long costumerId;
    private HashMap<Long, Integer> inventory = new HashMap<>();

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(ShoppingCart shoppingCart){
        setId(shoppingCart.getId());
        setCostumerId(shoppingCart.getId());

        for(Map.Entry<Product, Integer> entry : shoppingCart.getInventory().entrySet()){
            inventory.put(entry.getKey().getId(), entry.getValue());
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(Long costumerId) {
        this.costumerId = costumerId;
    }

    public HashMap<Long, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<Long, Integer> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "id=" + id +
                ", costumerId=" + costumerId +
                ", inventory=" + inventory +
                '}';
    }
}
