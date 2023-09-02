package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.controllers.PromotionController;
import com.joaolucas.shopjj.exceptions.BadRequestException;
import com.joaolucas.shopjj.exceptions.ConflictException;
import com.joaolucas.shopjj.exceptions.ResourceNotFoundException;
import com.joaolucas.shopjj.models.dto.PromotionDTO;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.Promotion;
import com.joaolucas.shopjj.repositories.ProductRepository;
import com.joaolucas.shopjj.repositories.PromotionRepository;
import com.joaolucas.shopjj.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;

    public List<PromotionDTO> findAll(){
        return promotionRepository.findAll().stream().map(promotion -> new PromotionDTO(promotion).add(linkTo(methodOn(PromotionController.class).findById(promotion.getId())).withSelfRel())).toList();
    }

    public PromotionDTO findById(Long id){
        return new PromotionDTO(promotionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Promotion was not found with ID: " + id))).add(linkTo(methodOn(PromotionController.class).findById(id)).withSelfRel());
    }

    public PromotionDTO create(PromotionDTO promotionDTO){
        if(!DataValidation.isPromotionInfoValid(promotionDTO)) throw new BadRequestException("Promotion info is invalid!");

        Promotion promotion = new Promotion();

        promotion.setDescription(promotion.getDescription());
        promotion.setPercentage(promotionDTO.getPercentage());
        promotion.setStartDate(promotionDTO.getStartDate());
        promotion.setEndDate(promotionDTO.getEndDate());

        Promotion savedPromotion = promotionRepository.save(promotion);

        return new PromotionDTO(savedPromotion).add(linkTo(methodOn(PromotionController.class).findById(savedPromotion.getId())).withSelfRel());
    }
    public PromotionDTO update(Long id, PromotionDTO promotionDTO){
        if(!DataValidation.isPromotionInfoValid(promotionDTO)) throw new BadRequestException("Promotion info is invalid!");

        Promotion promotion = promotionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Promotion was not found with ID: " + id));

        if(promotionDTO.getDescription() != null) promotion.setDescription(promotionDTO.getDescription());
        if(promotionDTO.getPercentage() != null) promotion.setPercentage(promotionDTO.getPercentage());
        if(promotionDTO.getStartDate() != null) promotion.setStartDate(promotionDTO.getStartDate());
        if(promotionDTO.getEndDate() != null) promotion.setEndDate(promotionDTO.getEndDate());

        return new PromotionDTO(promotionRepository.save(promotion)).add(linkTo(methodOn(PromotionController.class).findById(id)).withSelfRel());
    }

    public void delete(Long id){
        Promotion promotion = promotionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Promotion was not found with ID: " + id));
        promotionRepository.delete(promotion);
    }

    public void addProduct(Long promotionId, Long productId){
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion was not found with ID: " + promotionId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product was not found with ID: " + productId));

        if(promotion.getProducts().contains(product)) throw new ConflictException("Product is already on promotion!");
        promotion.getProducts().add(product);
        product.getPromotions().add(promotion);


        productRepository.save(product);
        promotionRepository.save(promotion);
    }

    public void removeProduct(Long promotionId, Long productId){
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion was not found with ID: " + promotionId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product was not found with ID: " + productId));

        if(!promotion.getProducts().contains(product)) throw new ConflictException("Product is not on promotion!");
        promotion.getProducts().remove(product);
        product.getPromotions().remove(promotion);

        productRepository.save(product);
        promotionRepository.save(promotion);
    }
}
