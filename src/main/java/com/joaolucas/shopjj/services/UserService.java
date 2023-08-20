package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.UserDTO;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    public UserDTO findById(Long id){
        return new UserDTO(userRepository.findById(id).orElseThrow());
    }

    public UserDTO create(UserDTO userDTO){
        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCpf(userDTO.getCpf());
        user.setGender(userDTO.getGender());

        return new UserDTO(userRepository.save(user));
    }

    public UserDTO update(Long id, UserDTO updateRequest){
        User user = userRepository.findById(id).orElseThrow();

        if(updateRequest.getFirstName() != null) updateRequest.setFirstName(updateRequest.getFirstName());
        if(updateRequest.getLastName() != null) updateRequest.setLastName(updateRequest.getLastName());
        if(updateRequest.getEmail() != null) updateRequest.setEmail(updateRequest.getEmail());
        if(updateRequest.getGender() != null) updateRequest.setGender(updateRequest.getGender());

        return new UserDTO(userRepository.save(user));
    }

    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }
}
