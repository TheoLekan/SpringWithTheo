package com.springwiththeo.week6.password_encoding_session.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements CommandLineRunner {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                        auth -> auth.anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();

    }


    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        String idForEncode = "argon2"; // use argon2 for new passwords

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }

    /**
     * Replaced the below with delegating password encoder
     * This allows us to use multiple password encoders and specify which one to use for new passwords.

    @Bean(name = "bcryptPasswordEncoder")
    PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Primary
    @Bean(name = "argonPasswordEncoder")
    PasswordEncoder argonPasswordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }*/

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails adminUser = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN").build();

        UserDetails oldUser = User.withUsername("old")
                .password(new BCryptPasswordEncoder().encode("old123"))
                .roles("USER").build();

        // In a real application, you would load users from a database or another source.
        return new InMemoryUserDetailsManager(adminUser, oldUser);
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println(
                // "Password for 'admin' user: " + userDetailsService(bcryptPasswordEncoder()).loadUserByUsername("admin").getPassword()
        );
        // System.out.println("This is admin user's hashed password rehashed: " + bcryptPasswordEncoder().encode(userDetailsService(bcryptPasswordEncoder()).loadUserByUsername("admin").getPassword()) + "\nNotice that that they're different, this is because this is a one way hashing algorithm, and it will always generate a different hash for the same password input. This is a security feature to prevent rainbow table attacks.");


    }
}
