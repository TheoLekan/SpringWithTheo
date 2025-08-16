package com.springwiththeo.week8.db_jwt_authentication.dto;

public record TokenResponse(String accessToken, String refreshToken, String tokenType, long expiresInSeconds) {
    // This record will automatically generate a constructor, getters, and equals/hashCode methods
    // for the accessToken and refreshToken fields.
}
