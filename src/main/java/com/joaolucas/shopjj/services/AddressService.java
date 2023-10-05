package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.controllers.AddressController;
import com.joaolucas.shopjj.exceptions.BadRequestException;
import com.joaolucas.shopjj.exceptions.ResourceNotFoundException;
import com.joaolucas.shopjj.models.dto.AddressDTO;
import com.joaolucas.shopjj.models.entities.Address;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.repositories.AddressRepository;
import com.joaolucas.shopjj.repositories.UserRepository;
import com.joaolucas.shopjj.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public List<AddressDTO> findAll(){
        return addressRepository.findAll().stream().map(address -> new AddressDTO(address).add(linkTo(methodOn(AddressController.class).findById(address.getId())).withSelfRel())).toList();
    }

    public AddressDTO findById(Long id){
        return new AddressDTO(addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address was not found with ID: " + id))).add(linkTo(methodOn(AddressController.class).findById(id)).withSelfRel());
    }

    public AddressDTO create(Long residentId, AddressDTO addressDTO){
        if(!DataValidation.isAddressInfoValid(addressDTO)) throw new BadRequestException("Address info is invalid!");

        Address address = new Address();
        User resident = userRepository.findById(residentId).orElseThrow(() -> new ResourceNotFoundException("User was not found with ID: " + residentId));

        address.setResident(resident);
        address.setState(addressDTO.getState());
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setComplement(addressDTO.getComplement());
        address.setCep(addressDTO.getCep());

        Address savedAddress = addressRepository.save(address);

        resident.getAddresses().add(address);

        return new AddressDTO(savedAddress).add(linkTo(methodOn(AddressController.class).findById(savedAddress.getId())).withSelfRel());
    }

    public AddressDTO update(Long id, AddressDTO addressDTO){
        if(!DataValidation.isAddressInfoValid(addressDTO)) throw new BadRequestException("Address info is invalid!");

        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address was not found with ID: " + id));
        if(addressDTO.getState() != null) address.setState(addressDTO.getState());
        if(addressDTO.getCity() != null) address.setCity(addressDTO.getCity());
        if(addressDTO.getStreet() != null) address.setStreet(addressDTO.getStreet());
        if(addressDTO.getNumber() != null) address.setNumber(addressDTO.getNumber());
        if(addressDTO.getComplement() != null) address.setComplement(addressDTO.getComplement());
        if(addressDTO.getCep() != null) address.setCep(addressDTO.getCep());

        return new AddressDTO(addressRepository.save(address)).add(linkTo(methodOn(AddressController.class).findById(id)).withSelfRel());
    }

    public void delete(Long id){
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address was not found with ID: " + id));
        addressRepository.delete(address);
    }

}
