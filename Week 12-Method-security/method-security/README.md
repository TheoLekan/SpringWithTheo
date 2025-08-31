# 🔄 Week 12 – Exploring Method Security Annotations

This week, I expanded on method-level authorization in Spring Security beyond `@PreAuthorize`.  
I learned and implemented three additional annotations that allow for more flexible and fine-grained access control:
- `@PostAuthorize`
- `@PreFilter`
- `@PostFilter`

---

## 🎯 Goal
While `@PreAuthorize` lets us decide *before* a method runs whether a user is allowed to execute it, real-world cases often require more nuance:
- Sometimes we must inspect the **returned object** before granting access.
- Sometimes we must filter a **list of inputs** before a method executes.
- Sometimes we must filter a **list of outputs** after a method executes.

Spring Security provides `@PostAuthorize`, `@PreFilter`, and `@PostFilter` for these use cases.

---

## ✅ What I Implemented

### 🔐 1. `@PostAuthorize`
- Secures a method **after it executes**, checking authorization based on the returned object.

**Example:**

```java
@GetMapping("/{id}")
@PostAuthorize("returnObject.ownerId() == authentication.principal.id or hasRole('ADMIN')")
public Report getReport(@PathVariable Long id) {
    return new Report(id, 100L, "User 100's Report");
}
```

✅ Users can only fetch their own reports.  
✅ Admins can fetch any report.

---

### 👥 2. `@PreFilter`
- Filters **input collections** before the method executes.
- Useful for batch operations where the client submits a list of IDs.

**Example:**

```java
@PostMapping("/batch")
@PreFilter("filterObject == authentication.principal.id or hasRole('ADMIN')")
public List<Long> processUsers(@RequestBody List<Long> userIds) {
    return userIds; // already filtered by Spring Security
}
```

✅ A normal user only passes their own ID.  
✅ Admins pass all IDs.

---

### 📜 3. `@PostFilter`
- Filters **output collections** after the method executes.
- Ensures users only see what they are authorized to.

**Example:**

```java
@GetMapping
@PostFilter("filterObject.ownerId() == authentication.principal.id or hasRole('ADMIN')")
public List<Report> getAllReports() {
    return List.of(
        new Report(1L, 100L, "User 100's Report"),
        new Report(2L, 200L, "User 200's Report"),
        new Report(3L, 100L, "Another report for User 100")
    );
}
```

✅ Users only see their own reports.  
✅ Admins see all reports.

---

## 🧪 Testing
- Logged in as **User 100**:
    - `/reports/1` → accessible.
    - `/reports/2` → forbidden.
    - `/users/batch` with `[100,200,300]` → returns `[100]`.
    - `/reports` → returns only reports owned by user 100.

- Logged in as **Admin**:
    - All endpoints return all data.

---

## 📝 Notes
- Records work great as DTOs in Spring. Remember to use accessor names in SpEL (e.g., `ownerId()` instead of `ownerId`).
- `filterObject` is the keyword used inside `@PreFilter` and `@PostFilter` to represent each item in the collection.
- `@PostAuthorize` is powerful, but you should avoid heavy queries before a 403 is thrown — better for read-heavy situations.

---

## 🔗 Follow the journey
📍 LinkedIn: Theo Olalekan  
📍 GitHub: Spring With Theo

---

#SpringWithTheo #NoPostNoPeace #Java #SpringBoot #SpringSecurity #MethodSecurity #LearningInPublic  