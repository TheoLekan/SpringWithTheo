package com.springwiththeo.week6.password_encoding_session.service;

import com.springwiththeo.week6.password_encoding_session.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        if (request.password().length() > 256) {
            throw new IllegalArgumentException("Password is too long");
        }
        if (request.password().startsWith("{")) {
            throw new IllegalArgumentException("Password must not be pre-encoded.");
        }
        try {
            userDetailsService.loadUserByUsername(request.username());
            throw new IllegalArgumentException("Username already exists");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException(illegalArgumentException.getMessage());
        } catch (UsernameNotFoundException usernameNotFoundException) {
            // This is expected if the user does not exist
            String encodedPassword = passwordEncoder.encode(request.password());

            UserDetails userDetails = User.builder()
                    .username(request.username())
                    .password(encodedPassword)
                    .roles("USER")
                    .build();

            ((InMemoryUserDetailsManager) userDetailsService).createUser(userDetails);
        }


    }
}
