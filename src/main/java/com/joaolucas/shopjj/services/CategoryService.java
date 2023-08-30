package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.CategoryDTO;
import com.joaolucas.shopjj.models.entities.Category;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.repositories.CategoryRepository;
import com.joaolucas.shopjj.repositories.ProductRepository;
import com.joaolucas.shopjj.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<CategoryDTO> findAll(){
        return categoryRepository.findAll().stream().map(CategoryDTO::new).toList();
    }

    public CategoryDTO findById(Long id){
        return new CategoryDTO(categoryRepository.findById(id).orElseThrow());
    }

    public CategoryDTO create(CategoryDTO categoryDTO){
        if(!DataValidation.isCategoryInfoValid(categoryDTO)) throw new RuntimeException();

        Category category = new Category();

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return new CategoryDTO(categoryRepository.save(category));
    }

    public CategoryDTO update(Long id, CategoryDTO categoryDTO){
        if(!DataValidation.isCategoryInfoValid(categoryDTO)) throw new RuntimeException();

        Category category = categoryRepository.findById(id).orElseThrow();
        if(categoryDTO.getName() != null) category.setName(categoryDTO.getName());
        if(categoryDTO.getDescription() != null) category.setDescription(categoryDTO.getDescription());

        return new CategoryDTO(categoryRepository.save(category));

    }

    public void delete(Long id){
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(category);
    }

    public void addProduct(Long categoryId, Long productId){
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        if(category.getProducts().contains(product) || product.getCategories().contains(category)) throw new RuntimeException();

        category.getProducts().add(product);
        product.getCategories().add(category);

        categoryRepository.save(category);
        productRepository.save(product);
    }

    public void removeProduct(Long categoryId, Long productId){
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        if(!category.getProducts().contains(product) || !product.getCategories().contains(category)) throw new RuntimeException();

        category.getProducts().remove(product);
        product.getCategories().remove(category);

        categoryRepository.save(category);
        productRepository.save(product);
    }
}
