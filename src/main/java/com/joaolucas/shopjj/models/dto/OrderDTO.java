package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.*;
import com.joaolucas.shopjj.models.enums.OrderStatus;
import com.joaolucas.shopjj.models.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDTO {
    private Long id;
    private Long costumerId;
    private BigDecimal totalPrice;
    private Long addressId;
    private LocalDateTime instant;
    private PaymentMethod paymentMethod;
    private OrderStatus orderStatus;
    private HashMap<Long, Integer> inventory = new HashMap<>();
    private List<Long> couponsId;

    public OrderDTO(){

    }

    public OrderDTO(Order order){
        setId(order.getId());
        setCostumerId(order.getCostumer().getId());
        setTotalPrice(order.getTotalPrice());
        setAddressId(order.getAddress().getId());
        setInstant(order.getInstant());
        setPaymentMethod(order.getPaymentMethod());
        setOrderStatus(order.getOrderStatus());
        setCouponsId(order.getCoupons().stream().map(Coupon::getId).toList());

        for(Map.Entry<Product, Integer> entry : order.getInventory().entrySet()){
            var quantity = entry.getValue();
            var product = entry.getKey();

            inventory.put(product.getId(), quantity);
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    public void setInstant(LocalDateTime instant) {
        this.instant = instant;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public HashMap<Long, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<Long, Integer> inventory) {
        this.inventory = inventory;
    }

    public List<Long> getCouponsId() {
        return couponsId;
    }

    public void setCouponsId(List<Long> couponsId) {
        this.couponsId = couponsId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", costumerId=" + costumerId +
                ", totalPrice=" + totalPrice +
                ", addressId=" + addressId +
                ", instant=" + instant +
                ", paymentMethod=" + paymentMethod +
                ", orderStatus=" + orderStatus +
                ", inventory=" + inventory +
                ", couponsId=" + couponsId +
                '}';
    }
}
