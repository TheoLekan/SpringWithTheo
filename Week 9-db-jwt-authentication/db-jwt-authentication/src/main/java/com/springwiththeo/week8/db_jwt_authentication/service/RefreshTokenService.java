package com.springwiththeo.week8.db_jwt_authentication.service;

import com.springwiththeo.week8.db_jwt_authentication.repository.RefreshToken;
import com.springwiththeo.week8.db_jwt_authentication.repository.RefreshTokenRepository;
import com.springwiththeo.week8.db_jwt_authentication.repository.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final Duration refreshTokenValidity = Duration.ofDays(30);

    private final SecureRandom secureRandom = new SecureRandom();

    private String generateOpaqueToken() {
        byte[] tokenBytes = new byte[64];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    public RefreshToken createRefreshToken(User user, boolean oneActivePerUser) {
        if (oneActivePerUser){
            // Delete existing refresh tokens for the user if only one active token is allowed
            refreshTokenRepository.deleteAllByUser(user);
        }
        return createRefreshToken(user);
    }

    private RefreshToken createRefreshToken(User user) {
        String token = generateOpaqueToken();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(token);
        refreshToken.setUser(user);
        refreshToken.setExpiryAt(Instant.now().plus(refreshTokenValidity));
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyRefreshToken(String token) throws IllegalArgumentException {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid or expired refresh token"));

        if (refreshToken.getExpiryAt().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new IllegalArgumentException("Refresh token has expired");
        }

        return refreshToken;
    }

    public RefreshToken tokenRotation(RefreshToken refreshToken) {
        // Delete the old token
        refreshTokenRepository.delete(refreshToken);
        // Create a new token for the same user
        return createRefreshToken(refreshToken.getUser(),false);
    }

    public void revokeAllFor(User user) {
        // Delete all refresh tokens for the user
        refreshTokenRepository.deleteAllByUser(user);
    }

    public void revokeToken(String token){
        refreshTokenRepository.findByToken(token)
                .ifPresent(refreshTokenRepository::delete);
    }
}

