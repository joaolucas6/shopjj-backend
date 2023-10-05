package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.UserDTO;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.repositories.ShoppingCartRepository;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    private UserService underTest;
    private User user;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository, shoppingCartRepository);
        user = new User();
    }

    @Test
    void itShouldFindAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        var result = underTest.findAll();

        assertThat(result).isEqualTo(List.of(new UserDTO(user)));
    }

    @Test
    void itShouldFindUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        var result = underTest.findById(1L);

        assertThat(result).isEqualTo(new UserDTO(user));
    }

    @Test
    void itShouldUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        UserDTO toUpdate = new UserDTO(user);
        user.setFirstName("jiwon");

        var result = underTest.update(1L, toUpdate);

        assertThat(result).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("jiwon");
    }

    @Test
    void itShouldDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        underTest.delete(1L);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void itShouldLoadUserByEmail() {
        when(userRepository.findByEmail("jiwon@gmail.com")).thenReturn(Optional.of(user));

        var result = underTest.loadUserByUsername("jiwon@gmail.com");

        assertThat(result).isEqualTo(user);
    }
}