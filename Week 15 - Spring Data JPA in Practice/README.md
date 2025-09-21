# 🔄 Week 15 – Spring Data JPA in Practice

This week focused on **Spring Data JPA**, the abstraction layer that sits on top of JPA/Hibernate and removes most of the boilerplate code for data access. I learned how repositories, queries, projections, paging, and transactions come together to build clean, production-ready persistence layers.

---

## 🎯 Goal
Understand how to use Spring Data JPA repositories to:
- Simplify CRUD operations
- Generate queries from method names
- Write custom queries when needed
- Use projections to avoid over-fetching
- Apply paging and sorting
- Manage transactions properly

---

## ✅ What I Learned

### **Day 1 – Spring Data Repositories**
- Repository hierarchy:
    - `CrudRepository<T, ID>` → basic CRUD
    - `PagingAndSortingRepository<T, ID>` → adds paging + sorting
    - `JpaRepository<T, ID>` → the all-in-one, most common
- Best practice: **extend `JpaRepository`** in real apps.

---

### **Day 2 – Derived Query Methods**
- Spring generates queries from method names:
    - `findByEmail`, `findByFirstNameAndLastName`
    - Keywords: `And`, `Or`, `Between`, `LessThan`, `Like`, `IsNull`, `OrderBy`
- Supports flexible return types: entity, `Optional`, `List`, `Stream`, projections.
- Handy tricks:
    - Nested property navigation (`findByAuthor_Name`)
    - Limiting results (`findTop3ByOrderByCreatedAtDesc`)

---

### **Day 3 – Custom Queries with `@Query`**
- Use JPQL for more complex queries:
  ```java
  @Query("select u from User u where u.email = :email")
  User findByEmail(@Param("email") String email);
  ```
- `nativeQuery = true` for DB-specific SQL.
- Common mistakes:
    - Using DB column names instead of entity fields
    - Forgetting `@Param`
    - Wrong return types
    - Lazy-loading issues (fix with `JOIN FETCH`)

---

### **Day 4 – Projections in Repositories**
- **Interface projection** → only specific fields
- **Record/class projection** → DTOs with constructors
- **Nested projection** → projections inside projections
- Benefits:
    - Fetch only needed data
    - Reduce query cost
    - Cleaner DTO handling in APIs

---

### **Day 5 – Paging & Sorting**
- `Sort`:
  ```java
  userRepository.findAll(Sort.by("lastName").ascending());
  ```
- `Pageable`:
  ```java
  Page<User> page = userRepository.findAll(PageRequest.of(0, 5));
  ```
- Paging works with custom queries (`findByAuthorName(String, Pageable)`).
- Perfect for REST APIs: small chunks, with metadata (page, size, total).

---

### **Day 6 – Transactions**
- `@Transactional` ensures atomic DB operations.
- Rollback on exceptions by default.
- Best placed at **service layer**, not controllers.
- Propagation modes:
    - `REQUIRED`, `REQUIRES_NEW`, `MANDATORY`, `NESTED`
- `@Transactional(readOnly = true)` for query optimization.

---

## 📝 Notes
- Spring Data JPA = productivity boost + cleaner repositories.
- Derived queries for simple cases, `@Query` for complex ones.
- Use projections to avoid over-fetching.
- Always paginate in list APIs.
- Keep transactional logic in the service layer.

---

## 🔗 Follow the Journey
- LinkedIn: [Theo Olalekan](https://www.linkedin.com/in/theo-olalekan)
- GitHub: [Spring With Theo](https://github.com/TheoLekan/SpringWithTheo)

#SpringWithTheo #NoPostNoPeace #Java #SpringBoot #SpringDataJPA #Transactions #Repositories