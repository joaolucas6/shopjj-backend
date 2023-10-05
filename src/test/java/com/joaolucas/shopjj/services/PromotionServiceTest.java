package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.PromotionDTO;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.Promotion;
import com.joaolucas.shopjj.repositories.ProductRepository;
import com.joaolucas.shopjj.repositories.PromotionRepository;
import org.junit.jupiter.api.AfterEach;
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
class PromotionServiceTest {

    @Mock
    private PromotionRepository promotionRepository;
    @Mock
    private ProductRepository productRepository;
    private PromotionService underTest;
    private Promotion promotion;
    private Product product;

    @BeforeEach
    void setUp() {
        underTest = new PromotionService(promotionRepository, productRepository);
        promotion = new Promotion();
    }

    @Test
    void itShouldFindAllPromotions() {
        when(promotionRepository.findAll()).thenReturn(List.of(promotion));

        var result = underTest.findAll();

        assertThat(result).isEqualTo(List.of(new PromotionDTO(promotion)));
    }

    @Test
    void itShouldFindPromotionById() {
        when(promotionRepository.findById(1L)).thenReturn(Optional.of(promotion));

        var result = underTest.findById(1L);

        assertThat(result).isEqualTo(new PromotionDTO(promotion));
    }

    @Test
    void itShouldCreatePromotion(){
        when(promotionRepository.save(promotion)).thenReturn(promotion);

        var result = underTest.create(new PromotionDTO(promotion));

        assertThat(result).isNotNull();
    }

    @Test
    void itShouldUpdatePromotion() {
        when(promotionRepository.findById(1L)).thenReturn(Optional.of(promotion));
        when(promotionRepository.save(promotion)).thenReturn(promotion);

        PromotionDTO toUpdate = new PromotionDTO(promotion);
        toUpdate.setDescription("gerente ficou maluco");

        var result = underTest.update(1L, toUpdate);

        assertThat(result).isNotNull();
        assertThat(promotion.getDescription()).isEqualTo("gerente ficou maluco");
    }

    @Test
    void itShouldDeletePromotion() {
        when(promotionRepository.findById(1L)).thenReturn(Optional.of(promotion));

        underTest.delete(1L);

        verify(promotionRepository, times(1)).delete(promotion);
    }

    @Test
    void itShouldAddProductToPromotion() {
        product = new Product();
        when(promotionRepository.findById(1L)).thenReturn(Optional.of(promotion));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        underTest.addProduct(1L, 1L);

        assertThat(product.getPromotions().contains(promotion) && promotion.getProducts().contains(product)).isTrue();
    }

    @Test
    void itShouldRemoveProductFromPromotion() {
        product = new Product();
        when(promotionRepository.findById(1L)).thenReturn(Optional.of(promotion));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        product.getPromotions().add(promotion);
        promotion.getProducts().add(product);

        underTest.removeProduct(1L, 1L);

        assertThat(product.getPromotions().contains(promotion) || promotion.getProducts().contains(product)).isFalse();
    }
}