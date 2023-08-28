package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.PromotionDTO;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.Promotion;
import com.joaolucas.shopjj.repositories.ProductRepository;
import com.joaolucas.shopjj.repositories.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;

    public List<PromotionDTO> findAll(){
        return promotionRepository.findAll().stream().map(PromotionDTO::new).toList();
    }

    public PromotionDTO findById(Long id){
        return new PromotionDTO(promotionRepository.findById(id).orElseThrow());
    }

    public PromotionDTO create(PromotionDTO promotionDTO){
        Promotion promotion = new Promotion();

        promotion.setDescription(promotion.getDescription());
        promotion.setPercentage(promotionDTO.getPercentage());
        promotion.setStartDate(promotionDTO.getStartDate());
        promotion.setEndDate(promotionDTO.getEndDate());


        return new PromotionDTO(promotionRepository.save(promotion));
    }
    public PromotionDTO update(Long id, PromotionDTO promotionDTO){
        Promotion promotion = promotionRepository.findById(id).orElseThrow();

        if(promotionDTO.getDescription() != null) promotion.setDescription(promotionDTO.getDescription());
        if(promotionDTO.getPercentage() != null) promotion.setPercentage(promotionDTO.getPercentage());
        if(promotionDTO.getStartDate() != null) promotion.setStartDate(promotionDTO.getStartDate());
        if(promotionDTO.getEndDate() != null) promotion.setEndDate(promotionDTO.getEndDate());

        return new PromotionDTO(promotionRepository.save(promotion));
    }

    public void delete(Long id){
        Promotion promotion = promotionRepository.findById(id).orElseThrow();
        promotionRepository.delete(promotion);
    }

    public void addProducts(Long promotionId, List<Product> products){
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow();
        products.forEach(product -> {
            if(promotion.getProducts().contains(product)) throw new RuntimeException();
            promotion.getProducts().add(product);
            product.getPromotions().add(promotion);
        });

        productRepository.saveAll(products);
        promotionRepository.save(promotion);
    }

    public void removeProducts(Long promotionId, List<Product> products){
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow();
        products.forEach(product -> {
            if(!promotion.getProducts().contains(product)) throw new RuntimeException();
            promotion.getProducts().remove(product);
            product.getPromotions().remove(promotion);
        });

        productRepository.saveAll(products);
        promotionRepository.save(promotion);
    }
}
