package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.AddressDTO;
import com.joaolucas.shopjj.models.entities.Address;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.repositories.AddressRepository;
import com.joaolucas.shopjj.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private UserRepository userRepository;
    private AddressService underTest;
    private Address address;
    private User user;

    @BeforeEach
    void setUp() {
        underTest = new AddressService(addressRepository, userRepository);
        address = new Address();
    }

    @Test
    void itShouldFindAllAddresses() {
        when(addressRepository.findAll()).thenReturn(List.of(address));
        var result = underTest.findAll();
        assertThat(result).isEqualTo(List.of(new AddressDTO(address)));
    }

    @Test
    void itShouldFindAddressById() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        var result = underTest.findById(1L);
        assertThat(result).isEqualTo(new AddressDTO(address));
    }

    @Test
    void itShouldCreateAddress() {
        user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(addressRepository.save(address)).thenReturn(address);

        var result = underTest.create(1L, new AddressDTO(address));

        assertThat(result).isNotNull();
        assertThat(user.getAddresses().contains(address)).isTrue();
    }

    @Test
    void itShouldUpdateAddress() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(addressRepository.save(address)).thenReturn(address);

        AddressDTO toUpdate = new AddressDTO(address);
        toUpdate.setCity("Lorena");

        var result = underTest.update(1L, toUpdate);

        assertThat(result).isNotNull();
        assertThat(address.getCity()).isEqualTo("Lorena");
    }

    @Test
    void itShouldDeleteAddress() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        underTest.delete(1L);
        verify(addressRepository, times(1)).delete(address);
    }
}