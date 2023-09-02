package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.controllers.OrderController;
import com.joaolucas.shopjj.exceptions.BadRequestException;
import com.joaolucas.shopjj.exceptions.ConflictException;
import com.joaolucas.shopjj.exceptions.ResourceNotFoundException;
import com.joaolucas.shopjj.models.dto.OrderDTO;
import com.joaolucas.shopjj.models.entities.*;
import com.joaolucas.shopjj.models.enums.OrderStatus;
import com.joaolucas.shopjj.repositories.*;
import com.joaolucas.shopjj.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final ProductRepository productRepository;

    public List<OrderDTO> findAll(){
        return orderRepository.findAll().stream().map(order -> new OrderDTO(order).add(linkTo(methodOn(OrderController.class).findById(order.getId())).withSelfRel())).toList();
    }

    public OrderDTO findById(Long id){
        return new OrderDTO(orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order was not found with ID: " + id))).add(linkTo(methodOn(OrderController.class).findById(id)).withSelfRel());
    }

    public OrderDTO create(Long costumerId, OrderDTO orderDTO){
        if(!DataValidation.isOrderInfoValid(orderDTO)) throw new BadRequestException("Order info is invalid!");

        Order order = new Order();
        Address address = addressRepository.findById(orderDTO.getAddressId()).orElseThrow(() -> new ResourceNotFoundException("Address was not found with ID: " + orderDTO.getAddressId()));
        User costumer = userRepository.findById(costumerId).orElseThrow(() -> new ResourceNotFoundException("User was not found with ID: " + costumerId));

        order.setAddress(address);
        order.setInstant(LocalDateTime.now());
        order.setPaymentMethod(orderDTO.getPaymentMethod());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCostumer(costumer);
        order.setCoupons(orderDTO.getCouponsId().stream().map(couponId -> couponRepository.findById(couponId).orElseThrow(() -> new ResourceNotFoundException("Coupon was not found with ID: " + couponId))).toList());
        order.setTotalPrice(BigDecimal.valueOf(0));

        HashMap<Product, Integer> inventory = new HashMap<>();

        for(Map.Entry<Long, Integer> entry : orderDTO.getInventory().entrySet()){
            Product product = productRepository.findById(entry.getKey()).orElseThrow(() -> new ResourceNotFoundException("Product was not found with ID: " + entry.getKey()));
            inventory.put(product, entry.getValue());
        }

        order.setInventory(inventory);


        for(Map.Entry<Product, Integer> entry : order.getInventory().entrySet()){
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            for(Promotion promotion : product.getPromotions()){
                if(promotion.getStartDate().isBefore(LocalDateTime.now()) && promotion.getEndDate().isAfter(LocalDateTime.now())){
                    BigDecimal valueToDiscount = product.getPrice().multiply(BigDecimal.valueOf(promotion.getPercentage()));
                    product.setPrice(product.getPrice().min(valueToDiscount));
                }
            }

            order.setTotalPrice(order.getTotalPrice().add(product.getPrice().multiply(BigDecimal.valueOf(quantity))));
        }

        orderDTO.getCouponsId().forEach(couponId -> {
            Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new ResourceNotFoundException("Coupon was not found with ID: " + couponId));
            if(coupon.getValidity().isBefore(LocalDateTime.now())) throw new ConflictException("Coupon is expired!");
            BigDecimal valueToDiscount = order.getTotalPrice().multiply(BigDecimal.valueOf(coupon.getPercentage()));
            order.setTotalPrice(order.getTotalPrice().min(valueToDiscount));
        });

        Order savedOrder = orderRepository.save(order);

        return new OrderDTO(savedOrder).add(linkTo(methodOn(OrderController.class).findById(savedOrder.getId())).withSelfRel());
    }

    public OrderDTO update(Long id, OrderDTO orderDTO){
        if(!DataValidation.isOrderInfoValid(orderDTO)) throw new BadRequestException("Order info is invalid!");

        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order was not found with ID: " + id));

        if(orderDTO.getAddressId() != null) {
            Address address = addressRepository.findById(orderDTO.getAddressId()).orElseThrow(() -> new ResourceNotFoundException("Address was not found with ID: " + orderDTO.getAddressId()));
            order.setAddress(address);
        }
        if(orderDTO.getPaymentMethod() != null) order.setPaymentMethod(orderDTO.getPaymentMethod());
        if(orderDTO.getOrderStatus() != null) order.setOrderStatus(orderDTO.getOrderStatus());

        return new OrderDTO(orderRepository.save(order)).add(linkTo(methodOn(OrderController.class).findById(id)).withSelfRel());
    }

    public void delete(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order was not found with ID: " + id));
        orderRepository.delete(order);
    }
}
