package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.ProductDTO;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService underTest;
    private Product product;

    @BeforeEach
    void setUp() {
        underTest = new ProductService(productRepository);
        product = new Product();
    }

    @Test
    void itShouldFindAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        var result = underTest.findAll();

        assertThat(result).isEqualTo(List.of(new ProductDTO(product)));
    }

    @Test
    void itShouldFindProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        var result = underTest.findById(1L);

        assertThat(result).isEqualTo(new ProductDTO(product));
    }

    @Test
    void itShouldCreateProduct() {
        when(productRepository.save(product)).thenReturn(product);

        var result = underTest.create(new ProductDTO(product));

        assertThat(result).isNotNull();
    }

    @Test
    void itShouldUpdateProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        ProductDTO toUpdate = new ProductDTO(product);
        product.setName("kpop album");

        var result = underTest.update(1L, toUpdate);

        assertThat(result).isNotNull();
        assertThat(product.getName()).isEqualTo("kpop album");
    }

    @Test
    void itShouldDeleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        underTest.delete(1L);
        verify(productRepository, times(1)).delete(product);
    }
}