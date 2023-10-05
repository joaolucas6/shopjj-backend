package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Coupon;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;

public class CouponDTO extends RepresentationModel<CouponDTO> {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponDTO couponDTO = (CouponDTO) o;
        return Objects.equals(id, couponDTO.id) && Objects.equals(name, couponDTO.name) && Objects.equals(description, couponDTO.description) && Objects.equals(percentage, couponDTO.percentage) && Objects.equals(validity, couponDTO.validity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, description, percentage, validity);
    }
}
