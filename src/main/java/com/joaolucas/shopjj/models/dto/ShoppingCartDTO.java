package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.ShoppingCart;

import java.util.List;

public class ShoppingCartDTO {

    private Long id;
    private Long costumerId;
    private List<Long> productsId;

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(ShoppingCart shoppingCart){
        setId(shoppingCart.getId());
        setCostumerId(shoppingCart.getId());
        setProductsId(shoppingCart.getProducts().stream().map(Product::getId).toList());
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

    public List<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Long> productsId) {
        this.productsId = productsId;
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "id=" + id +
                ", costumerId=" + costumerId +
                ", productsId=" + productsId +
                '}';
    }
}
