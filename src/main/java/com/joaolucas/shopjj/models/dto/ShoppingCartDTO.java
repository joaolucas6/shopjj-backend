package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.ShoppingCart;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartDTO that = (ShoppingCartDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(costumerId, that.costumerId) && Objects.equals(inventory, that.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, costumerId, inventory);
    }
}
