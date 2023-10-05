package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.ShoppingCartDTO;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.ShoppingCart;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.repositories.ProductRepository;
import com.joaolucas.shopjj.repositories.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private ProductRepository productRepository;
    private ShoppingCartService underTest;
    private ShoppingCart shoppingCart;
    private User user;
    private Product product;

    @BeforeEach
    void setUp() {
        underTest = new ShoppingCartService(shoppingCartRepository, productRepository);
        shoppingCart = new ShoppingCart();
    }

    @Test
    void itShouldFindAllShoppingCarts() {
        when(shoppingCartRepository.findAll()).thenReturn(List.of(shoppingCart));

        var result = underTest.findAll();

        assertThat(result).isEqualTo(List.of(new ShoppingCartDTO(shoppingCart)));
    }

    @Test
    void itShouldFindShoppingCartById() {
        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(shoppingCart));

        var result = underTest.findById(1L);

        assertThat(result).isEqualTo(new ShoppingCartDTO(shoppingCart));
    }

    @Test
    void itShouldAddItem() {
        product = new Product();

        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(shoppingCart));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        underTest.addItem(1L, 1L, 1);

        assertThat(shoppingCart.getInventory().containsKey(product)).isTrue();
    }

    @Test
    void itShouldRemoveItem() {
        product = new Product();

        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(shoppingCart));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        shoppingCart.getInventory().put(product, 1);

        underTest.removeItem(1L, 1L, 1);

        assertThat(shoppingCart.getInventory().containsKey(product)).isFalse();
    }
}