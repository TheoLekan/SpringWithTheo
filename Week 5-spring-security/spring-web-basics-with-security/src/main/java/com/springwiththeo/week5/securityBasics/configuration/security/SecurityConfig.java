package com.springwiththeo.week5.securityBasics.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity in this example
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().authenticated() // Require authentication for all requests
                )
                .formLogin(Customizer.withDefaults())
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

        // Create an in-memory manager details manager with the admin manager
        return new InMemoryUserDetailsManager(admin, manager);
    }


}
