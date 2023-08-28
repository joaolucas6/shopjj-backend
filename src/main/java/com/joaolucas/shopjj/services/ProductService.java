package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.ProductDTO;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.repositories.ProductRepository;
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
        return new ProductDTO(productRepository.findById(id).orElseThrow());
    }

    public ProductDTO create(ProductDTO productDTO){
        Product product = new Product();
        product.setName(product.getName());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(product.getImageUrl());
        product.setPrice(product.getPrice());
        product.setAvailableQuantity(product.getAvailableQuantity());

        return new ProductDTO(productRepository.save(product));
    }

    public ProductDTO update(Long id, ProductDTO productDTO){
        Product product = productRepository.findById(id).orElseThrow();

        if(productDTO.getName() != null) product.setName(productDTO.getName());
        if(productDTO.getDescription() != null) product.setDescription(productDTO.getDescription());
        if(productDTO.getImageUrl() != null) product.setImageUrl(productDTO.getImageUrl());
        if(productDTO.getPrice() != null) product.setPrice(productDTO.getPrice());
        if(productDTO.getAvailableQuantity() != null) product.setAvailableQuantity(product.getAvailableQuantity());

        return new ProductDTO(productRepository.save(product));
    }

    public void delete(Long id){
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }


}
