package com.joaolucas.shopjj.models.entities;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Objects;

@Entity
@Table(name = "tb_shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User costumer;

    @ElementCollection
    private HashMap<Product, Integer> inventory = new HashMap<>();

    public ShoppingCart(){

    }

    public ShoppingCart(Long id, User costumer) {
        this.id = id;
        this.costumer = costumer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCostumer() {
        return costumer;
    }

    public void setCostumer(User costumer) {
        this.costumer = costumer;
    }

    public HashMap<Product, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<Product, Integer> inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ShoppingCart that = (ShoppingCart) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", costumer=" + costumer +
                ", inventory=" + inventory +
                '}';
    }


}
