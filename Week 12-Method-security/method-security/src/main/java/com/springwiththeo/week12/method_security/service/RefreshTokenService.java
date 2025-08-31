package com.springwiththeo.week12.method_security.service;


import com.springwiththeo.week12.method_security.repository.RefreshToken;
import com.springwiththeo.week12.method_security.repository.RefreshTokenRepository;
import com.springwiththeo.week12.method_security.repository.User;
import com.springwiththeo.week12.method_security.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final UserRepository userRepository;
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
    public RefreshToken createRefreshToken(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));

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

    @Transactional
    @Scheduled(fixedRate= 1000 * 60 * 60) // 1 hour
    public void deleteExpiredTokens() {
        Instant expiryDate = Instant.now();
        int rowsDeleted = refreshTokenRepository.deleteByExpiryAtBefore(expiryDate);
        if (rowsDeleted>0) {
            System.out.println("Deleted " + rowsDeleted + " expired refresh tokens.");
        }

    }
}

