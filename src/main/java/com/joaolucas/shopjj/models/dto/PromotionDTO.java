package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.Promotion;

import java.time.LocalDateTime;
import java.util.List;

public class PromotionDTO {
    private Long id;
    private String description;
    private Double percentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Long> productsId;

    public PromotionDTO(){

    }

    public PromotionDTO(Promotion promotion){
        setId(promotion.getId());
        setDescription(promotion.getDescription());
        setPercentage(promotion.getPercentage());
        setStartDate(promotion.getStartDate());
        setEndDate(promotion.getEndDate());
        setProductsId(promotion.getProducts().stream().map(Product::getId).toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Long> productsId) {
        this.productsId = productsId;
    }

    @Override
    public String toString() {
        return "PromotionDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", percentage=" + percentage +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", productsId=" + productsId +
                '}';
    }
}
