package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.controllers.UserController;
import com.joaolucas.shopjj.exceptions.BadRequestException;
import com.joaolucas.shopjj.exceptions.ResourceNotFoundException;
import com.joaolucas.shopjj.models.dto.UserDTO;
import com.joaolucas.shopjj.models.entities.ShoppingCart;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.repositories.ShoppingCartRepository;
import com.joaolucas.shopjj.repositories.UserRepository;
import com.joaolucas.shopjj.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(user -> new UserDTO(user).add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel())).toList();
    }

    public UserDTO findById(Long id){
        return new UserDTO(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User was not found with ID: " + id))).add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
    }

    public UserDTO create(UserDTO userDTO){
        if(!DataValidation.isUserInfoValid(userDTO)) throw new BadRequestException("User info is invalid!");

        User user = new User();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCostumer(user);

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCpf(userDTO.getCpf());
        user.setGender(userDTO.getGender());
        user.setShoppingCart(shoppingCart);

        shoppingCartRepository.save(shoppingCart);

        User savedUser = userRepository.save(user);

        return new UserDTO(savedUser).add(linkTo(methodOn(UserController.class).findById(savedUser.getId())).withSelfRel());
    }

    public UserDTO update(Long id, UserDTO userDTO){
        if(!DataValidation.isUserInfoValid(userDTO)) throw new BadRequestException("User info is invalid!");
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User was not found with ID: " + id));

        if(userDTO.getFirstName() != null) user.setFirstName(userDTO.getFirstName());
        if(userDTO.getLastName() != null) user.setLastName(userDTO.getLastName());
        if(userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if(userDTO.getGender() != null) user.setGender(userDTO.getGender());

        return new UserDTO(userRepository.save(user)).add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
    }

    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User was not found with ID: " + id));
        userRepository.delete(user);
    }



}
