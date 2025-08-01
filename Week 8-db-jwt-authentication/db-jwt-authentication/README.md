# 🔐 Week 8 – Database-Backed Users + JWT Authentication

This week, I upgraded my Spring Security setup from session-based auth to **JWT-based stateless authentication** — a huge step toward building production-ready APIs.

The goal:
- **Authenticate users from a database** using `DaoAuthenticationProvider`
- **Issue JWT tokens** on login instead of sessions
- **Validate tokens** on every request using a custom filter
- **Go fully stateless** (no redirects, no form login)

---

## ✅ What I Implemented

### 1. **Database-Backed Authentication**
- Configured `DaoAuthenticationProvider` to use `CustomUserDetailsService` and `PasswordEncoder` for verifying credentials against the DB.

### 2. **JWT Utility**
- Built `JwtService` to:
    - Generate signed tokens with `HS256`.
    - Extract usernames from tokens.
    - Validate tokens (signature + expiry).

### 3. **Login Endpoint**
- Created `/auth/login`:
    - Accepts username & password.
    - Authenticates via `AuthenticationManager`.
    - Returns a signed JWT.

### 4. **Custom JWT Filter**
- Built `JwtAuthenticationFilter` (extends `OncePerRequestFilter`):
    - Extracts `Authorization: Bearer <token>` header.
    - Validates token using `JwtService`.
    - Loads user details and sets the `SecurityContext`.

### 5. **Stateless Security Config**
- Disabled form login & CSRF.
- Set `SessionCreationPolicy.STATELESS`.
- Added `JwtAuthenticationFilter` **before** `UsernamePasswordAuthenticationFilter`.

---

## 🧰 Technologies Used
- Spring Boot
- Spring Security
- JJWT (io.jsonwebtoken)
- BCryptPasswordEncoder
- DaoAuthenticationProvider
- In-memory & database-backed UserDetails

---

## 📁 Folder Structure
```
week8-db-jwt-auth/
├── configuration/
│   ├── SecurityConfig.java
│   └── JwtAuthenticationFilter.java
├── controller/
│   └── AuthController.java
│   └── TestController.java
├── repository/
│   ├── UserRepository.java
│   └── User.java
├── service/
│   ├── JwtService.java
│   └── CustomUserDetailsService.java
└── DbJwtAuthenticationApplication.java
```

---

## 📝 Notes
- Every request must now include `Authorization: Bearer <token>`.
- No sessions, no redirects — this is pure API-style authentication.
- Invalid tokens → `401 Unauthorized`.
- Valid token but insufficient role → `403 Forbidden`.

---

## 🔗 Follow the journey
📍 LinkedIn: [Theo Olalekan](https://www.linkedin.com/in/theoolalekan/)  
📍 GitHub: [Spring With Theo](https://github.com/TheoLekan/SpringWithTheo)

#SpringWithTheo #NoPostNoPeace #Java #SpringBoot #SpringSecurity #JWT #LearningInPublic #BuildInPublic