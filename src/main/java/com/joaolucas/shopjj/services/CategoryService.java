package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.exceptions.BadRequestException;
import com.joaolucas.shopjj.exceptions.ConflictException;
import com.joaolucas.shopjj.exceptions.ResourceNotFoundException;
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
        return new CategoryDTO(categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category was not found with ID: " + id)));
    }

    public CategoryDTO create(CategoryDTO categoryDTO){
        if(!DataValidation.isCategoryInfoValid(categoryDTO)) throw new BadRequestException("Category info is invalid!");

        Category category = new Category();

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return new CategoryDTO(categoryRepository.save(category));
    }

    public CategoryDTO update(Long id, CategoryDTO categoryDTO){
        if(!DataValidation.isCategoryInfoValid(categoryDTO)) throw new BadRequestException("Category info is invalid!");

        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category was not found with ID: " + id));
        if(categoryDTO.getName() != null) category.setName(categoryDTO.getName());
        if(categoryDTO.getDescription() != null) category.setDescription(categoryDTO.getDescription());

        return new CategoryDTO(categoryRepository.save(category));

    }

    public void delete(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category was not found with ID: " + id));
        categoryRepository.delete(category);
    }

    public void addProduct(Long categoryId, Long productId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category was not found with ID: " + categoryId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product was not found with ID: " + productId));

        if(category.getProducts().contains(product) || product.getCategories().contains(category)) throw new ConflictException("Product is already added in category!");

        category.getProducts().add(product);
        product.getCategories().add(category);

        categoryRepository.save(category);
        productRepository.save(product);
    }

    public void removeProduct(Long categoryId, Long productId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category was not found with ID: " + categoryId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product was not found with ID: " + productId));

        if(!category.getProducts().contains(product) || !product.getCategories().contains(category)) throw new ConflictException("Product is not in category list");

        category.getProducts().remove(product);
        product.getCategories().remove(category);

        categoryRepository.save(category);
        productRepository.save(product);
    }
}
