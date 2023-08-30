package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.AddressDTO;
import com.joaolucas.shopjj.models.entities.Address;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.repositories.AddressRepository;
import com.joaolucas.shopjj.repositories.UserRepository;
import com.joaolucas.shopjj.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public List<AddressDTO> findAll(){
        return addressRepository.findAll().stream().map(AddressDTO::new).toList();
    }

    public AddressDTO findById(Long id){
        return new AddressDTO(addressRepository.findById(id).orElseThrow());
    }

    public AddressDTO create(Long residentId, AddressDTO addressDTO){
        if(!DataValidation.isAddressInfoValid(addressDTO)) throw new RuntimeException();

        Address address = new Address();
        User resident = userRepository.findById(residentId).orElseThrow();

        address.setResident(resident);
        address.setState(addressDTO.getState());
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setComplement(addressDTO.getComplement());
        address.setCep(addressDTO.getCep());

        return new AddressDTO(addressRepository.save(address));
    }

    public AddressDTO update(Long id, AddressDTO addressDTO){
        if(!DataValidation.isAddressInfoValid(addressDTO)) throw new RuntimeException();

        Address address = addressRepository.findById(id).orElseThrow();
        if(addressDTO.getState() != null) address.setState(addressDTO.getState());
        if(addressDTO.getCity() != null) address.setCity(addressDTO.getCity());
        if(addressDTO.getStreet() != null) address.setStreet(addressDTO.getStreet());
        if(addressDTO.getNumber() != null) address.setNumber(addressDTO.getNumber());
        if(addressDTO.getComplement() != null) address.setComplement(addressDTO.getComplement());
        if(addressDTO.getCep() != null) address.setCep(addressDTO.getCep());

        return new AddressDTO(addressRepository.save(address));
    }

    public void delete(Long id){
        Address address = addressRepository.findById(id).orElseThrow();
        addressRepository.delete(address);
    }

}
