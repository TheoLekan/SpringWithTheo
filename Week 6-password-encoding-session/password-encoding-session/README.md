# 🛡️ Week 6 – Secure Password Handling

This week, I explored how to safely store and manage user passwords using Spring Security.  
The goal was to implement a realistic registration flow that:

- Validates password length and format
- Encodes new passwords using the configured `PasswordEncoder`
- Prevents double-encoding (e.g. users submitting `{bcrypt}...` by mistake)
- Stores users using Spring's `InMemoryUserDetailsManager` (for now)

---

## ✅ What I Implemented

### 🔐 1. Registration DTO

```java
public record RegisterRequest(String username, String password) {
}
```

---

### 🧠 2. `UserService`

- Validates password length (`< 256`)
- Blocks passwords starting with `{...}` to prevent pre-encoded hashes
- Encodes using Argon2 via `PasswordEncoder`
- Registers user using in-memory storage

---

### 🧪 3. `/api/auth/register` Endpoint

- Exposed via `RegisterController`
- Accepts POST requests with JSON body
- Returns 200 OK on success or 400 Bad Request on validation failure

---

## 🧰 Technologies Used

- Spring Boot
- Spring Security
- Argon2PasswordEncoder (via DelegatingPasswordEncoder)
- InMemoryUserDetailsManager
- Postman for testing

---

## 📝 Notes

- In real apps, swap `InMemoryUserDetailsManager` for a database-backed `UserDetailsService`
- Spring makes password validation easy to upgrade in the future using DelegatingPasswordEncoder

---

## 📁 Folder Structure

```
week6-password-encoding-session/
├── src/
    └── com/springwiththeo/week6/password_encoding_session/
        ├── controller/
        │   └── RegisterController.java
        ├── dto/
        │   └── RegisterRequest.java
        ├── service/
        │   └── UserService.java
        ├── config/
        │   └── SecurityConfig.java
        └── SpringWithTheoApplication.java
├── README.md
```

---

## 🔗 Follow the journey

📍 LinkedIn: [theoolalekan](https://www.linkedin.com/in/theoolalekan/)  
📍 GitHub: [Spring With Theo](https://github.com/TheoLekan/SpringWithTheo)

#SpringWithTheo #NoPostNoPeace #Java #SpringBoot #SpringSecurity #PasswordEncoder #LearningInPublic