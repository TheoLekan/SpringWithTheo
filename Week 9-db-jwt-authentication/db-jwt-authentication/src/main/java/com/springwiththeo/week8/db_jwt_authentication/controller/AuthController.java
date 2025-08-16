package com.springwiththeo.week8.db_jwt_authentication.controller;

import com.springwiththeo.week8.db_jwt_authentication.dto.RefreshRequest;
import com.springwiththeo.week8.db_jwt_authentication.dto.TokenResponse;
import com.springwiththeo.week8.db_jwt_authentication.repository.RefreshToken;
import com.springwiththeo.week8.db_jwt_authentication.repository.User;
import com.springwiththeo.week8.db_jwt_authentication.service.JwtService;
import com.springwiththeo.week8.db_jwt_authentication.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestParam String username,@RequestParam String password) {
        // Authenticate the user
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Generate JWT token
        String jwtToken = jwtService.generateToken(authenticate.getName(), authenticate.getAuthorities());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(authenticate.getName());
        TokenResponse body = new TokenResponse(jwtToken, refreshToken.getToken(), "Bearer", 15);
        return ResponseEntity.ok(body);

    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshRequest refreshTokenRequest) {
        RefreshToken validRefreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.refreshToken());

        User user = validRefreshToken.getUser();
        var authorities = user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

        //create a new JWT token (stateless)
        String newToken = jwtService.generateToken(user.getUsername(), authorities);

        //rotate the refresh token (stateful)
        RefreshToken newRefreshToken = refreshTokenService.tokenRotation(validRefreshToken);

        TokenResponse body = new TokenResponse(newToken, newRefreshToken.getToken(), "Bearer",30);

        return ResponseEntity.ok(body);

    }

}
