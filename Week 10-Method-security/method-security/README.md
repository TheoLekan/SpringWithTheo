# 🔄 Week 10 – Fine-Grained Method Security with Spring Security

This week, I implemented **method-level authorization** to tighten security rules and enforce business logic directly at the service and controller layer.

---

## 🎯 Goal
Endpoint-level rules (`HttpSecurity`) are good, but too broad. In real-world apps, you often need **fine-grained control**:

- A normal user should only be able to access **their own data**.
- An admin should be able to access **any data**.

Spring Security’s `@PreAuthorize` lets us express these rules declaratively, using Spring Expression Language (SpEL).

---

## ✅ What I Implemented

### 🔐 1. Enabled Method Security
- Added `@EnableMethodSecurity` to the security configuration.
- This unlocked annotation-based authorization support (`@PreAuthorize`, `@PostAuthorize`, etc.).

---

### 👤 2. Custom Principal with `CustomUserDetails`
- Created a `CustomUserDetails` class implementing `UserDetails`.
- Extended the principal to carry both `username` and **user `id`** (required for ownership checks).

---

### ⚙️ 3. JWT Service & Claims
- Updated `JwtService` to include `id` and `roles` in JWT claims.
- Added `extractId()` method to retrieve the `id` from tokens.

---

### 📜 4. JWT Filter Update
- Modified the filter so that instead of setting the principal to just a `String username`,  
  it rebuilds a full `CustomUserDetails` from JWT claims (id, username, roles).
- This enabled SpEL expressions like `authentication.principal.id`.

---

### 🚪 5. Secured Endpoint with SpEL
In `UserController`:

```java
@GetMapping("/{id}")
@PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
public User getUserById(@PathVariable Long id) {
    return userService.findById(id);
}
```
- Users can only fetch their own record.
- Admins can fetch any record.

---

## 🧪 Testing
- Logged in as a **normal user** → could only call `/users/{theirId}`.
- Tried `/users/{otherId}` → **forbidden**.
- Logged in as **admin** → could access **any** user.

---

## 📝 Notes
- `@PreAuthorize` brings business rules closer to the domain logic.
- Always enrich the `principal` with fields your rules will need (like `id`).
- JWT filters should rebuild the `principal` from claims to avoid unnecessary DB hits.
- SpEL is powerful — but keep expressions **simple and maintainable**.

---

## 🔗 Follow the journey
📍 LinkedIn: Theo Olalekan  
📍 GitHub: Spring With Theo

---

#SpringWithTheo #NoPostNoPeace #Java #SpringBoot #SpringSecurity #MethodSecurity  
