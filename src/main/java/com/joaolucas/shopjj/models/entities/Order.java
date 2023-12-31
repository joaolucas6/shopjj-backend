package com.joaolucas.shopjj.models.entities;

import com.joaolucas.shopjj.models.enums.OrderStatus;
import com.joaolucas.shopjj.models.enums.PaymentMethod;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "costumer_id")
    private User costumer;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "instant")
    private LocalDateTime instant;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ElementCollection
    private HashMap<Product, Integer> inventory = new HashMap<>();

    @ElementCollection
    private List<Coupon> coupons = new ArrayList<>();

    public Order(){

    }

    public Order(Long id, User costumer, BigDecimal totalPrice, Address address, LocalDateTime instant, PaymentMethod paymentMethod, OrderStatus orderStatus) {
        this.id = id;
        this.costumer = costumer;
        this.totalPrice = totalPrice;
        this.address = address;
        this.instant = instant;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public HashMap<Product, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<Product, Integer> inventory) {
        this.inventory = inventory;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", costumer=" + costumer +
                ", totalPrice=" + totalPrice +
                ", address=" + address +
                ", instant=" + instant +
                ", paymentMethod=" + paymentMethod +
                ", orderStatus=" + orderStatus +
                ", inventory=" + inventory +
                ", coupons=" + coupons +
                '}';
    }
}
