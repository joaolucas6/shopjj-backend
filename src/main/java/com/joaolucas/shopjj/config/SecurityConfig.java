package com.joaolucas.shopjj.config;

import com.joaolucas.shopjj.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;
    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()

                        .requestMatchers(HttpMethod.PUT,  "/api/v1/promotions/**")
                        .hasRole("MANAGER")

                        .requestMatchers(HttpMethod.POST, "/api/v1/promotions/**")
                        .hasRole("MANAGER")

                        .requestMatchers(HttpMethod.DELETE, "/api/v1/promotions/**")
                        .hasRole("MANAGER")

                        .requestMatchers(HttpMethod.POST, "/api/v1/products/**")
                        .hasRole("MANAGER")

                        .requestMatchers(HttpMethod.PUT, "/api/v1/products/**")
                        .hasRole("MANAGER")

                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**")
                        .hasRole("MANAGER")

                        .requestMatchers("/api/v1/coupons/**")
                        .hasRole("MANAGER")

                        .requestMatchers(HttpMethod.POST,"/api/v1/categories")
                        .hasRole("MANAGER")

                        .requestMatchers(HttpMethod.PUT,"/api/v1/categories")
                        .hasRole("MANAGER")

                        .requestMatchers(HttpMethod.DELETE,"/api/v1/categories")
                        .hasRole("MANAGER")

                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
