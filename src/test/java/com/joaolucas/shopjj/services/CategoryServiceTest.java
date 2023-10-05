package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.CategoryDTO;
import com.joaolucas.shopjj.models.entities.Category;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.repositories.CategoryRepository;
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
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductRepository productRepository;
    private CategoryService underTest;
    private Category category;
    private Product product;

    @BeforeEach
    void setUp() {
        underTest = new CategoryService(categoryRepository, productRepository);
        category = new Category();
    }

    @Test
    void itShouldFindAllCategories() {
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        var result = underTest.findAll();

        assertThat(result).isEqualTo(List.of(new CategoryDTO(category)));
    }

    @Test
    void itShouldFindCategoriesById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        var result = underTest.findById(1L);

        assertThat(result).isEqualTo(new CategoryDTO(category));
    }

    @Test
    void itShouldCreateCategory() {
        when(categoryRepository.save(category)).thenReturn(category);

        var result = underTest.create(new CategoryDTO(category));

        assertThat(result).isNotNull();
    }

    @Test
    void itShouldUpdateCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);

        CategoryDTO toUpdate = new CategoryDTO(category);
        toUpdate.setName("new jeans");

        var result = underTest.update(1L, toUpdate);

        assertThat(result).isNotNull();
        assertThat(category.getName()).isEqualTo("new jeans");
    }

    @Test
    void itShouldDeleteCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        underTest.delete(1L);
        verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    void itShouldAddItemToCategory() {
        product = new Product();
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        underTest.addProduct(1L, 1L);

        assertThat(product.getCategories().contains(category) && category.getProducts().contains(product)).isTrue();
    }

    @Test
    void itShouldRemoveItemFromCategory() {
        product = new Product();
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        product.getCategories().add(category);
        category.getProducts().add(product);

        underTest.removeProduct(1L, 1L);

        assertThat(product.getCategories().contains(category) || category.getProducts().contains(product)).isFalse();
    }
}