package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Category;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.Promotion;
import com.joaolucas.shopjj.models.entities.Review;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer availableQuantity;
    private List<Long> categoriesId;
    private List<Long> reviewsId;
    private List<Long> promotionsId;

    public ProductDTO(){}

    public ProductDTO(Product product){
        setId(product.getId());
        setName(product.getName());
        setDescription(product.getDescription());
        setImageUrl(product.getImageUrl());
        setPrice(product.getPrice());
        setAvailableQuantity(product.getAvailableQuantity());
        setCategoriesId(product.getCategories().stream().map(Category::getId).toList());
        setReviewsId(product.getReviews().stream().map(Review::getId).toList());
        setPromotionsId(product.getPromotions().stream().map(Promotion::getId).toList());
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public List<Long> getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(List<Long> categoriesId) {
        this.categoriesId = categoriesId;
    }

    public List<Long> getReviewsId() {
        return reviewsId;
    }

    public void setReviewsId(List<Long> reviewsId) {
        this.reviewsId = reviewsId;
    }

    public List<Long> getPromotionsId() {
        return promotionsId;
    }

    public void setPromotionsId(List<Long> promotionsId) {
        this.promotionsId = promotionsId;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity +
                ", categoriesId=" + categoriesId +
                ", reviewsId=" + reviewsId +
                ", promotionsId=" + promotionsId +
                '}';
    }
}
