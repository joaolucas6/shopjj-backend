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
    @JoinColumn(name = "client_id")
    private User client;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "address")
    private Address address;

    @Column(name = "instant")
    private LocalDateTime instant;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ElementCollection
    private HashMap<Integer, Product> inventory = new HashMap<>();

    @ElementCollection
    private List<Coupon> coupons = new ArrayList<>();

    public Order(){

    }

    public Order(Long id, User client, BigDecimal totalPrice, Address address, LocalDateTime instant, PaymentMethod paymentMethod, OrderStatus orderStatus) {
        this.id = id;
        this.client = client;
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

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
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

    public HashMap<Integer, Product> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<Integer, Product> inventory) {
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
                ", client=" + client +
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
