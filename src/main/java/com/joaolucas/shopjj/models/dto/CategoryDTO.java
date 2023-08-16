package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Category;
import com.joaolucas.shopjj.models.entities.Product;

import java.util.List;

public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private List<Long> productsId;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        setId(category.getId());
        setName(category.getName());
        setDescription(category.getDescription());
        setProductsId(category.getProducts().stream().map(Product::getId).toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Long> productsId) {
        this.productsId = productsId;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productsId=" + productsId +
                '}';
    }
}
