package com.tuchamba.tuchamba_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desactiva CSRF (útil para desarrollo)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite todos los requests sin login
                );
        return http.build();
    }
}
