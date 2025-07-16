package com.springwiththeo.week6.password_encoding_session.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements CommandLineRunner {

    @Bean
    PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails adminUser = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN").build();
        // In a real application, you would load users from a database or another source.
        return new InMemoryUserDetailsManager(adminUser);
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println(
                "Password for 'admin' user: " + userDetailsService(bcryptPasswordEncoder()).loadUserByUsername("admin").getPassword()
        );
        System.out.println("This is admin user's hashed password rehashed: " + bcryptPasswordEncoder().encode(userDetailsService(bcryptPasswordEncoder()).loadUserByUsername("admin").getPassword()) + "\nNotice that that they're different, this is because this is a one way hashing algorithm, and it will always generate a different hash for the same password input. This is a security feature to prevent rainbow table attacks.");


    }
}
