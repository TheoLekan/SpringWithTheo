# 🛡️ Week 7 – Understanding UserDetails, Sessions & Security Filters

This week, I focused on **how Spring Security authenticates and manages users**, as well as **how sessions and filters
fit into the security chain**.  
The goal was to gain a deeper understanding of:

- How `UserDetails` and `UserDetailsService` work under the hood
- How Spring Security manages **sessions** (stateful vs stateless)
- How to **configure and order filters** in the `SecurityFilterChain`

---

## ✅ What I Learned

### 1️⃣ `UserDetails` & `UserDetailsService`

- `UserDetails` represents the **authenticated user** in Spring Security.
- `UserDetailsService` is used to **load user data** during authentication (in-memory, DB, or custom sources).
- Learned how Spring uses these in the authentication process (via `DaoAuthenticationProvider`).

---

### 2️⃣ Session Management

- Explored **Spring Security’s session strategies**:
  - `IF_REQUIRED` (default)
  - `NEVER`
  - `STATELESS`
- Learned how to **configure max concurrent sessions** and **session fixation protection**.
- Understood the role of `SessionManagementFilter` in enforcing these rules.

---

### 3️⃣ Spring Security Filters

- Understood the **fixed order of filters** inside `FilterChainProxy` (e.g., `SecurityContextPersistenceFilter`,
  `UsernamePasswordAuthenticationFilter`, `AnonymousAuthenticationFilter`).
- Learned how to **insert custom filters** using `addFilterBefore`/`addFilterAfter` relative to existing filters.
- Clarified the difference between **servlet container filters** and **Spring Security filters**.

---

### 4️⃣ Exception Flow

- Learned that **exceptions thrown in filters don’t reach `@ExceptionHandler`** or `@ControllerAdvice`.
- Understood how `ExceptionTranslationFilter` catches `AuthenticationException` and `AccessDeniedException` to redirect
  or return proper responses.

---

## 🧰 Technologies Used

- Spring Boot
- Spring Security
- InMemoryUserDetailsManager
- Session management configuration
- Security filter chain customization

---

## 📝 Notes

- **Order of builder calls in `HttpSecurity` doesn’t affect runtime filter order** — Spring enforces a fixed internal
  order.
- **AnonymousAuthenticationFilter** automatically populates the context with an anonymous user when no authentication is
  found.
- **Sessions are powerful but risky** — learned how to restrict or disable them for APIs (stateless mode).

---

## 📁 Folder Structure

```
week7-userdetails-sessions-filters/
├── src/
    └── com/springwiththeo/week7/userdetails_sessions_filters/
        ├── config/
        │   └── SecurityConfig.java
        │   └── LoginFailure.java
        │   └── Successhandler.java         
        └── SpringWithTheoApplication.java
├── README.md
└── NOTES.md
```

---

## 🔗 Follow the journey

📍 LinkedIn: [theoolalekan](https://www.linkedin.com/in/theoolalekan/)  
📍 GitHub: [Spring With Theo](https://github.com/TheoLekan/SpringWithTheo)

#SpringWithTheo #NoPostNoPeace #Java #SpringBoot #SpringSecurity #LearningInPublic