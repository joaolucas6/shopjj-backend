package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.exceptions.BadRequestException;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.models.records.AuthenticationRequest;
import com.joaolucas.shopjj.models.records.AuthenticationResponse;
import com.joaolucas.shopjj.models.records.RegisterRequest;
import com.joaolucas.shopjj.models.records.RegisterResponse;
import com.joaolucas.shopjj.repositories.UserRepository;
import com.joaolucas.shopjj.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest request){
        if(!DataValidation.isRegisterRequestValid(request)) throw new BadRequestException("Register request info is invalid!");

        User user = new User();

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        User savedUser = userRepository.save(user);

        return new RegisterResponse(jwtService.generateToken(savedUser));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        );

        Authentication authentication = authenticationManager.authenticate(usernamePasswordToken);

        User user = (User) authentication.getPrincipal();

        return new AuthenticationResponse(jwtService.generateToken(user));
    }

}
