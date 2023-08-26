package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Coupon;

import java.time.LocalDateTime;

public class CouponDTO {
    private Long id;
    private String name;
    private String description;

    private Double percentage;

    private LocalDateTime validity;

    public CouponDTO(){

    }

    public CouponDTO(Coupon coupon){
        setId(coupon.getId());
        setName(coupon.getName());
        setDescription(coupon.getDescription());
        setValidity(coupon.getValidity());
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

    public LocalDateTime getValidity() {
        return validity;
    }

    public void setValidity(LocalDateTime validity) {
        this.validity = validity;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "CouponDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", percentage=" + percentage +
                ", validity=" + validity +
                '}';
    }
}
