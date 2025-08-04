package com.springwiththeo.week8.db_jwt_authentication.controller;

import com.springwiththeo.week8.db_jwt_authentication.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password) {
        // Authenticate the user
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Generate JWT token
        return "Add this token in Bearer header to authenticate with Jwt: "+jwtService.generateToken(authenticate.getName(), authenticate.getAuthorities());

    }


}
