package com.joaolucas.shopjj.utils;

import com.joaolucas.shopjj.models.dto.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;

public class DataValidation {

    public static boolean isUserInfoValid(UserDTO userDTO){
        if(isAllFieldsNull(userDTO)) return false;
        if(userDTO.getFirstName().length() > 50 || userDTO.getFirstName().isBlank()) return false;
        if(userDTO.getLastName().length() > 50 || userDTO.getLastName().isBlank()) return false;
        if(userDTO.getEmail().isBlank()) return false;
        if(userDTO.getCpf().isBlank()) return false;

        return true;
    }

    public static boolean isReviewInfoValid(ReviewDTO reviewDTO){
        if(isAllFieldsNull(reviewDTO)) return false;
        if(reviewDTO.getRating() > 10 || reviewDTO.getRating() < 0) return false;
        if(reviewDTO.getCommentary().length() > 5000 || reviewDTO.getCommentary().isBlank()) return false;

        return true;
    }

    public static boolean isPromotionInfoValid(PromotionDTO promotionDTO){
        if(isAllFieldsNull(promotionDTO)) return false;
        if(promotionDTO.getDescription().length() > 5000 || promotionDTO.getDescription().isBlank()) return false;
        if(promotionDTO.getPercentage() >= 1 || promotionDTO.getPercentage() <= 0) return false;
        if(promotionDTO.getStartDate().isAfter(promotionDTO.getEndDate())) return false;

        return true;
    }

    public static boolean isProductInfoValid(ProductDTO productDTO){
        if(isAllFieldsNull(productDTO)) return false;
        if(productDTO.getName().length() > 100 || productDTO.getName().isBlank()) return false;
        if(productDTO.getDescription().length() > 5000 || productDTO.getDescription().isBlank()) return false;
        if(productDTO.getImageUrl().isBlank()) return false;
        if(productDTO.getPrice().intValueExact() <= 0) return false;
        if(productDTO.getAvailableQuantity() < 0) return false;

        return true;
    }

    public static boolean isOrderInfoValid(OrderDTO orderDTO){
        if(isAllFieldsNull(orderDTO)) return false;
        for(Map.Entry<Long, Integer> entry : orderDTO.getInventory().entrySet()){
            if(entry.getValue() <= 0) return false;
        }

        return true;
    }

    public static boolean isCouponInfoValid(CouponDTO couponDTO){
        if(isAllFieldsNull(couponDTO)) return false;
        if(couponDTO.getName().length() > 100 || couponDTO.getName().isBlank()) return false;
        if(couponDTO.getDescription().length() > 5000 || couponDTO.getDescription().isBlank()) return false;
        if(couponDTO.getPercentage() >= 1 || couponDTO.getPercentage() <= 0) return false;
        if(couponDTO.getValidity().isBefore(LocalDateTime.now())) return false;

        return true;
    }

    public static boolean isCategoryInfoValid(CategoryDTO categoryDTO){
        if(isAllFieldsNull(categoryDTO)) return false;
        if(categoryDTO.getName().length() > 100 || categoryDTO.getName().isBlank()) return false;
        if(categoryDTO.getDescription().length() > 5000 || categoryDTO.getDescription().isBlank()) return false;

        return true;
    }

    public static boolean isAddressInfoValid(AddressDTO addressDTO){
        if(isAllFieldsNull(addressDTO)) return false;
        if(addressDTO.getState().length() > 50 || addressDTO.getState().isBlank()) return false;
        if(addressDTO.getCity().length() > 50 || addressDTO.getCity().isBlank()) return false;
        if(addressDTO.getStreet().length() > 150 || addressDTO.getStreet().isBlank()) return false;
        if(addressDTO.getComplement().length() > 150 || addressDTO.getComplement().isBlank()) return false;
        if(!(addressDTO.getCep().length() == 8) || addressDTO.getCep().isBlank()) return false;

        return true;
    }

    public static boolean isAllFieldsNull(Object object){
        Class<?> objectClass = object.getClass();
        for(Field field : objectClass.getFields()){
            if(field == null) return true;
        }

        return false;
    }
}
