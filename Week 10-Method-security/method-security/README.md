# 🔄 Week 9 – Implementing Refresh Tokens with Spring Security + JWT

This week, I built **refresh token support** into the authentication system so users can stay logged in without re-entering credentials while keeping sessions secure.

---

## 🎯 Goal
In real-world apps, JWT access tokens should be short-lived for security, but logging in every 15 minutes is a terrible user experience.  
The **refresh token pattern** solves this by issuing a second, longer-lived token that can request new access tokens without asking for the username/password again.

---

## ✅ What I Implemented

### 🗄 1. Refresh Token Repository & Entity
- Created a `RefreshToken` JPA entity with:
  - `id` (UUID)
  - `token` (securely generated string)
  - `user` (relation to `User` entity)
  - `expiryDate`
- Added `RefreshTokenRepository` for CRUD operations.

---

### ⚙ 2. Refresh Token Service
- Handles:
  - Generating refresh tokens for users
  - Validating token expiry
  - Rotating (invalidating and issuing a new one)
- Replaces old tokens to prevent reuse.

---

### 🔐 3. Integration with Authentication Flow
- When a user logs in:
  - Issue both **access token (JWT)** + **refresh token**
  - Save refresh token in DB
- When access token expires:
  - Client sends refresh token to `/api/auth/refresh`
  - If valid → issue new access token

---

### 🌐 4. Security Config Updates
- Updated `SecurityConfig` to allow `/api/auth/refresh` without authentication.
- Ensured refresh token endpoint still validates token signature + expiry.

---

## 🧪 Testing
- Used Postman to:
  1. Login → receive access token + refresh token
  2. Wait for access token expiry (15sec)
  3. Call `/api/auth/refresh` with refresh token
  4. Confirm receipt of a new valid access token

---

## 📝 Notes
- Refresh tokens should be stored securely (e.g., HttpOnly cookies in production).
- On logout, always invalidate the refresh token in the DB to prevent reuse.
- For high-security apps, consider rotating refresh tokens every use.

---

## 🔗 Follow the journey
📍 LinkedIn: [Theo Olalekan](https://www.linkedin.com/in/theoolalekan/)  
📍 GitHub: [Spring With Theo](https://github.com/TheoLekan/SpringWithTheo)

#SpringWithTheo #NoPostNoPeace #Java #SpringBoot #SpringSecurity #JWT #RefreshToken