package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.OrderDTO;
import com.joaolucas.shopjj.models.entities.*;
import com.joaolucas.shopjj.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CouponRepository couponRepository;
    @Mock
    private ProductRepository productRepository;
    private OrderService underTest;
    private Order order;
    private Address address;


    @BeforeEach
    void setUp() {
        underTest = new OrderService(orderRepository, addressRepository, userRepository, couponRepository, productRepository);
        order = new Order();
    }

    @Test
    void itShouldFindAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(order));

        var result = underTest.findAll();

        assertThat(result).isEqualTo(List.of(new OrderDTO(order)));
    }

    @Test
    void itShouldFindOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        var result = underTest.findById(1L);

        assertThat(result).isEqualTo(new OrderDTO(order));
    }

    @Test
    void itShouldCreateOrder() {
        address = new Address();
        Coupon coupon = new Coupon();
        User user = new User();

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(couponRepository.findById(1L)).thenReturn(Optional.of(coupon));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(orderRepository.save(order)).thenReturn(order);

        OrderDTO orderDTO = new OrderDTO(order);
        HashMap<Long, Integer> hashMap = new HashMap<>();
        orderDTO.setInventory(hashMap);
        orderDTO.setCouponsId(List.of(1L));
        orderDTO.setAddressId(1L);

        var result = underTest.create(1L, orderDTO);

        assertThat(result).isNotNull();
        assertThat(user.getOrders().contains(order)).isTrue();
    }

    @Test
    void itShouldUpdateOrder() {
        address = new Address();
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(orderRepository.save(order)).thenReturn(order);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        OrderDTO toUpdate = new OrderDTO(order);
        toUpdate.setAddressId(1L);

        var result = underTest.update(1L, toUpdate);

        assertThat(result).isNotNull();
        assertThat(order.getAddress()).isEqualTo(address);
    }

    @Test
    void itShouldDeleteOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        underTest.delete(1L);
        verify(orderRepository, times(1)).delete(order);
    }
}
