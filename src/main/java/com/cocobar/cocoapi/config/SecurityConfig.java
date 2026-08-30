package com.cocobar.cocoapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection because we are building a stateless REST API, not a website
                .csrf(AbstractHttpConfigurer::disable)
                // Configure which endpoints are public and which are private
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Allow anyone to register and login
                        // ALLOW DASHBOARD ACCESS (This fixes your 403 error)
                        .requestMatchers("/api/dashboard/**").permitAll()
                        //.anyRequest().authenticated() // Require login for all other future endpoints (like dashboard)
                        .requestMatchers("/api/inventory/**").permitAll() // NEW: Whitelist inventory endpoints
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}