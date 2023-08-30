package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.OrderDTO;
import com.joaolucas.shopjj.models.entities.*;
import com.joaolucas.shopjj.models.enums.OrderStatus;
import com.joaolucas.shopjj.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final ProductRepository productRepository;

    public List<OrderDTO> findAll(){
        return orderRepository.findAll().stream().map(OrderDTO::new).toList();
    }

    public OrderDTO findById(Long id){
        return new OrderDTO(orderRepository.findById(id).orElseThrow());
    }

    public OrderDTO create(Long costumerId, OrderDTO orderDTO){
        Order order = new Order();
        Address address = addressRepository.findById(orderDTO.getAddressId()).orElseThrow();
        User costumer = userRepository.findById(costumerId).orElseThrow();

        order.setAddress(address);
        order.setInstant(LocalDateTime.now());
        order.setPaymentMethod(orderDTO.getPaymentMethod());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCostumer(costumer);
        order.setCoupons(orderDTO.getCouponsId().stream().map(couponId -> couponRepository.findById(couponId).orElseThrow()).toList());
        order.setTotalPrice(BigDecimal.valueOf(0));

        HashMap<Product, Integer> inventory = new HashMap<>();

        for(Map.Entry<Long, Integer> entry : orderDTO.getInventory().entrySet()){
            Product product = productRepository.findById(entry.getKey()).orElseThrow();
            inventory.put(product, entry.getValue());
        }

        order.setInventory(inventory);


        for(Map.Entry<Product, Integer> entry : order.getInventory().entrySet()){
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            order.setTotalPrice(order.getTotalPrice().add(product.getPrice().multiply(BigDecimal.valueOf(quantity))));
        }

        orderDTO.getCouponsId().forEach(couponId -> {
            Coupon coupon = couponRepository.findById(couponId).orElseThrow();
            if(coupon.getValidity().isBefore(LocalDateTime.now())) throw new RuntimeException();
            BigDecimal valueToDiscount = order.getTotalPrice().multiply(BigDecimal.valueOf(coupon.getPercentage()));
            order.setTotalPrice(order.getTotalPrice().min(valueToDiscount));
        });


        return new OrderDTO(orderRepository.save(order));
    }

    public OrderDTO update(Long id, OrderDTO orderDTO){
        Order order = orderRepository.findById(id).orElseThrow();

        if(orderDTO.getAddressId() != null) {
            Address address = addressRepository.findById(orderDTO.getAddressId()).orElseThrow();
            order.setAddress(address);
        }
        if(orderDTO.getPaymentMethod() != null) order.setPaymentMethod(orderDTO.getPaymentMethod());
        if(orderDTO.getOrderStatus() != null) order.setOrderStatus(orderDTO.getOrderStatus());

        return new OrderDTO(orderRepository.save(order));
    }

    public void delete(Long id){
        Order order = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order);
    }
}
