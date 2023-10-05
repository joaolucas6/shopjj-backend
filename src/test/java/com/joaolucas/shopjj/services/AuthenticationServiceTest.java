package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.models.enums.Role;
import com.joaolucas.shopjj.models.records.AuthenticationResponse;
import com.joaolucas.shopjj.models.records.RegisterRequest;
import com.joaolucas.shopjj.models.records.RegisterResponse;
import com.joaolucas.shopjj.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    private AuthenticationService underTest;
    private User user;

    @BeforeEach
    void setUp() {
        underTest = new AuthenticationService(passwordEncoder, userRepository, jwtService, authenticationManager);
        user = new User();
    }

    @Test
    void itShouldRegisterUser() {
        when(userRepository.save(user)).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn("token");

        RegisterRequest request = new RegisterRequest("jo", "ao", "joao@gmail.com", "cruzeiroesporteclube", Role.MANAGER);

        var result = underTest.register(request);

        verify(userRepository, times(1)).save(user);

        assertThat(result).isInstanceOf(RegisterResponse.class);
    }

}