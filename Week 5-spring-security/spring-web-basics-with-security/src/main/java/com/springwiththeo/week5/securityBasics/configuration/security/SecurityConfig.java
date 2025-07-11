package com.springwiththeo.week5.securityBasics.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().authenticated() // Require authentication for all requests
                )
                .formLogin(Customizer.withDefaults());
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
                .withUsername("Theo")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .build();

        // Create an in-memory user details manager with the admin user
        return new InMemoryUserDetailsManager(admin);
    }


}
