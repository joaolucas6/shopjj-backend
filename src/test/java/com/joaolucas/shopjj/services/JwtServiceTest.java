package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.models.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {
    private JwtService underTest;
    private User user;
    private String token;
    private String SECRET_KEY = "A1B7E47AA1969488A8B66ABC9BCE6";
    private final long expiration = 86400000;

    @BeforeEach
    void setUp() {
        underTest = new JwtService(SECRET_KEY, expiration);

        user = new User();

        user.setFirstName("haerin");
        user.setLastName("haerin");
        user.setEmail("haerin@gmail.com");
        user.setPassword("haerin");
        user.setRole(Role.MANAGER);

        token = "invalid token";
    }

    @Test
    void itShouldGenerateToken() {
        var result = underTest.generateToken(user);
        assertThat(result).isInstanceOf(String.class);
    }

    @Test
    void itShouldThrowExceptionForInvalidToken() {
        Assertions.assertThrows(RuntimeException.class, () -> underTest.validateToken(token));
    }
}