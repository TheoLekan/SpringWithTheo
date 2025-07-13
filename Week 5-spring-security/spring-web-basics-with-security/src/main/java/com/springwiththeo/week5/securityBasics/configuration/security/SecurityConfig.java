package com.springwiththeo.week5.securityBasics.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.HashMap;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authencationFailureHandler;

    @Autowired
    public SecurityConfig(
            AuthenticationSuccessHandler authenticationSuccessHandler,
            AuthenticationFailureHandler authencationFailureHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authencationFailureHandler = authencationFailureHandler;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, ObjectMapper mapper) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity in this example
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/employees/admin/**").hasRole("ADMIN") // Only ADMIN can access /api/employees/admin/**
                        .requestMatchers("/api/employees/manager/**").hasRole("MANAGER") // ADMIN and MANAGER can access /api/employees/manager/**
                        .requestMatchers("/api/employees/user/**").hasRole("USER") // ADMIN and MANAGER can access /api/employees/manager/**
                        .anyRequest().authenticated() // Require authentication for all requests
                )
                .formLogin(form -> {
                    form.loginPage("/login") // Custom login page
                            .permitAll() // Allow all users to access the login page
                            .successHandler(authenticationSuccessHandler)
                            .failureHandler(authencationFailureHandler);
                }).
                exceptionHandling(exceptionHandling -> exceptionHandling
                                .authenticationEntryPoint((request, response, authException) -> {
                                    System.out.println("Unauthorized access: " + authException.getMessage());
                                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Set the response status to 401 Unauthorized
                                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);// Set the response content type to plain text
                                    HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                                    stringObjectHashMap.put("error", "Unauthorized access: " + authException.getMessage());
                                    response.getWriter().write(mapper.writeValueAsString(stringObjectHashMap)); // Write the error message to the response
                                })
                                .accessDeniedHandler((request, response, accessDeniedException) -> {
                                    System.out.println("Access denied: " + accessDeniedException.getMessage());
                                    response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Set the response status to 403 Forbidden
                                    response.setContentType(MediaType.APPLICATION_JSON_VALUE); // Set the response content type to plain text
                                    HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                                    stringObjectHashMap.put("error", "Access denied: " + accessDeniedException.getMessage());
                                    response.getWriter().write(mapper.writeValueAsString(stringObjectHashMap)); // Write the error message to the response
                                }))
                // Custom access denied page
                .httpBasic(Customizer.withDefaults()); // Use HTTP Basic authentication
        // Configure security settings here
        return httpSecurity.build(); // Replace with actual security filter chain configuration
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use a simple password encoder for demonstration purposes
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .build();

        UserDetails manager = User
                .withUsername("manager")
                .password(passwordEncoder.encode("password"))
                .roles("MANAGER")
                .build();

        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();
        // Create an in-memory manager details manager with the admin manager
        return new InMemoryUserDetailsManager(admin, manager, user);
    }


}
