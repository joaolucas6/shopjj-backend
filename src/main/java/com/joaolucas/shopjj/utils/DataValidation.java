package com.joaolucas.shopjj.utils;

import com.joaolucas.shopjj.models.dto.*;
import com.joaolucas.shopjj.models.records.RegisterRequest;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.regex.Pattern;

public class DataValidation {

    public static boolean isUserInfoValid(UserDTO userDTO){
        if(isAllFieldsNull(userDTO)) return false;
        if(userDTO.getFirstName() != null && userDTO.getFirstName().length() > 50 || userDTO.getFirstName() != null && userDTO.getFirstName().isBlank()) return false;
        if(userDTO.getLastName() != null && userDTO.getLastName().length() > 50 || userDTO.getLastName() != null &&  userDTO.getLastName().isBlank()) return false;
        if(userDTO.getEmail() != null && userDTO.getEmail().isBlank() || userDTO.getEmail() != null && !isEmailValid(userDTO.getEmail())) return false;
        if(userDTO.getCpf() != null && userDTO.getCpf().isBlank() || userDTO.getCpf() != null && !isCpfValid(userDTO.getCpf())) return false;

        return true;
    }

    public static boolean isReviewInfoValid(ReviewDTO reviewDTO){
        if(isAllFieldsNull(reviewDTO)) return false;
        if(reviewDTO.getRating() != null && reviewDTO.getRating() > 10 || reviewDTO.getRating() != null && reviewDTO.getRating() < 0) return false;
        if(reviewDTO.getCommentary() != null && reviewDTO.getCommentary().length() > 2000 || reviewDTO.getCommentary() != null && reviewDTO.getCommentary().isBlank()) return false;

        return true;
    }

    public static boolean isPromotionInfoValid(PromotionDTO promotionDTO){
        if(isAllFieldsNull(promotionDTO)) return false;
        if(promotionDTO.getDescription() != null && promotionDTO.getDescription().length() > 1000 || promotionDTO.getDescription() != null && promotionDTO.getDescription().isBlank()) return false;
        if(promotionDTO.getPercentage() != null && promotionDTO.getPercentage() >= 1 || promotionDTO.getPercentage() != null && promotionDTO.getPercentage() <= 0) return false;
        if(promotionDTO.getStartDate() != null && promotionDTO.getStartDate().isAfter(promotionDTO.getEndDate())) return false;

        return true;
    }

    public static boolean isProductInfoValid(ProductDTO productDTO){
        if(isAllFieldsNull(productDTO)) return false;
        if(productDTO.getName() != null && productDTO.getName().length() > 130 || productDTO.getName() != null && productDTO.getName().isBlank()) return false;
        if(productDTO.getDescription() != null && productDTO.getDescription().length() > 3000 || productDTO.getDescription() != null && productDTO.getDescription().isBlank()) return false;
        if(productDTO.getImageUrl() != null && productDTO.getImageUrl().isBlank()) return false;
        if(productDTO.getPrice() != null && productDTO.getPrice().intValueExact() <= 0) return false;
        if(productDTO.getAvailableQuantity() != null && productDTO.getAvailableQuantity() < 0) return false;

        return true;
    }

    public static boolean isOrderInfoValid(OrderDTO orderDTO){
        if(isAllFieldsNull(orderDTO)) return false;
        for(Map.Entry<Long, Integer> entry : orderDTO.getInventory().entrySet()){
            if(entry.getValue() != null && entry.getValue() <= 0) return false;
        }

        return true;
    }

    public static boolean isCouponInfoValid(CouponDTO couponDTO){
        if(isAllFieldsNull(couponDTO)) return false;
        if(couponDTO.getName() != null && couponDTO.getName().length() > 30 || couponDTO.getName() != null && couponDTO.getName().isBlank()) return false;
        if(couponDTO.getDescription() != null && couponDTO.getDescription().length() > 1000 || couponDTO.getDescription() != null && couponDTO.getDescription().isBlank()) return false;
        if(couponDTO.getPercentage() != null && couponDTO.getPercentage() >= 1 || couponDTO.getPercentage() != null && couponDTO.getPercentage() <= 0) return false;
        if(couponDTO.getValidity() != null && couponDTO.getValidity().isBefore(LocalDateTime.now())) return false;

        return true;
    }

    public static boolean isCategoryInfoValid(CategoryDTO categoryDTO){
        if(isAllFieldsNull(categoryDTO)) return false;
        if(categoryDTO.getName().length() > 100 || categoryDTO.getName().isBlank()) return false;
        if(categoryDTO.getDescription().length() > 1000 || categoryDTO.getDescription().isBlank()) return false;

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

    public static boolean isRegisterRequestValid(RegisterRequest registerRequest){
        if(registerRequest.firstName().length() > 50 || registerRequest.firstName().isBlank()) return false;
        if(registerRequest.lastName().length() > 50 || registerRequest.lastName().isBlank()) return false;
        if(registerRequest.email().isBlank() || !isEmailValid(registerRequest.email())) return false;
        if(registerRequest.password().isBlank() || registerRequest.password().length() < 8 || registerRequest.password().length() > 30) return false;

        return true;
    }

    public static boolean isCpfValid(String value){
        String cpf = value.replace(".", "").replace("-", "");

        String firstPart = cpf.substring(0, 9);

        int firstPartResult = 0;

        for(int i = 10, j = 0; i >= 2; i--, j++){
            int number = Integer.parseInt(String.valueOf(firstPart.charAt(j)));

            number *= i;
            firstPartResult += number;
        }

        if(firstPartResult * 10 % 11 !=  Integer.parseInt(String.valueOf(cpf.charAt(9)))) return false;

        int secondPartResult = 0;

        for(int i = 11, j = 0; i >= 2; i--, j++){

            int number = Integer.parseInt(String.valueOf(cpf.charAt(j)));

            number *= i;
            secondPartResult += number;
        }

        if(secondPartResult * 10 % 11 != Integer.parseInt(String.valueOf(cpf.charAt(10)))) return false;

        return true;
    }

    public static boolean isEmailValid(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        return pattern.matcher(email).matches();
    }

    public static boolean isAllFieldsNull(Object object){
        Class<?> objectClass = object.getClass();
        for(Field field : objectClass.getFields()){
            if(field == null) return true;
        }

        return false;
    }
}
