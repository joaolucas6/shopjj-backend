package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.exceptions.BadRequestException;
import com.joaolucas.shopjj.exceptions.ResourceNotFoundException;
import com.joaolucas.shopjj.models.dto.ProductDTO;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.repositories.ProductRepository;
import com.joaolucas.shopjj.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> findAll(){
        return productRepository.findAll().stream().map(ProductDTO::new).toList();
    }

    public ProductDTO findById(Long id){
        return new ProductDTO(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product was not found with ID: " + id)));
    }

    public ProductDTO create(ProductDTO productDTO){
        if(!DataValidation.isProductInfoValid(productDTO)) throw new BadRequestException("Product info is invalid");

        Product product = new Product();
        product.setName(product.getName());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(product.getImageUrl());
        product.setPrice(product.getPrice());
        product.setAvailableQuantity(product.getAvailableQuantity());

        return new ProductDTO(productRepository.save(product));
    }

    public ProductDTO update(Long id, ProductDTO productDTO){
        if(!DataValidation.isProductInfoValid(productDTO)) throw new BadRequestException("Product info is invalid");

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product was not found with ID: " + id));

        if(productDTO.getName() != null) product.setName(productDTO.getName());
        if(productDTO.getDescription() != null) product.setDescription(productDTO.getDescription());
        if(productDTO.getImageUrl() != null) product.setImageUrl(productDTO.getImageUrl());
        if(productDTO.getPrice() != null) product.setPrice(productDTO.getPrice());
        if(productDTO.getAvailableQuantity() != null) product.setAvailableQuantity(product.getAvailableQuantity());

        return new ProductDTO(productRepository.save(product));
    }

    public void delete(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product was not found with ID: " + id));
        productRepository.delete(product);
    }


}
