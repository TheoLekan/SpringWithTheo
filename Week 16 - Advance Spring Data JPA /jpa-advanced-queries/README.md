
# 🔄 Week 16 – Advanced Queries with Spring Data JPA

This week’s focus was building a **Library Search Service** 📚 to explore advanced query options in Spring Data JPA beyond derived queries.

---

## 🎯 Goal
Make querying more **flexible** and **dynamic**:
- Avoid dozens of repository methods for every filter combination.
- Support search forms where users may provide only some fields.
- Balance between simplicity (QBE) and power (Specifications).

---

## ✅ What I Implemented

### 1. Dynamic Queries with Specifications (Criteria API)
- Created reusable `Specification<Book>` filters:
  - `hasTitle(String title)`
  - `hasAuthor(String authorName)`
  - `publishedBetween(LocalDate start, LocalDate end)`
- Composed them dynamically with `.and()` / `.or()` or the new `Specification.allOf()` / `Specification.anyOf()` factories.
- Verified results in logs: one clean query with all conditions.

---

### 2. Query by Example (QBE)
- Used probe objects as search templates:
  ```java
  Book probe = new Book();
  probe.setGenre("Programming");
  Example<Book> example = Example.of(probe);
  ```
- Added `ExampleMatcher` for flexible string matching:
    - `withIgnoreCase()` → case-insensitive
    - `withMatcher("title", contains)` → partial match
    - `withIgnorePaths("id")` → skip unwanted fields
- Nested probes worked (e.g., `author.nationality = "US"`).

---

## 📝 Notes
- **QBE strengths**: simple equality or string-based searches without writing queries.
- **QBE limitation**: no ranges (`between`, `<`, `>`) or complex logic — use Specifications for those.
- **Specifications** are verbose but extremely powerful for dynamic, composable filters.

---

## 🔗 Follow the Journey
- 📍 LinkedIn: Theo Olalekan
- 📍 GitHub: [Spring With Theo](https://github.com/TheoLekan/SpringWithTheo)

#SpringWithTheo #Java #SpringBoot #SpringDataJPA #Specifications #QueryByExample #LearningInPublic
