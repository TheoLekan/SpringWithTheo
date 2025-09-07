# 🔄 Week 13 – JPA Projections & Relationships

This week I dove into two powerful parts of JPA: **projections** and **entity relationships**.  
These are critical for real-world apps because they help shape queries efficiently and model how data is actually
connected in a system.

---

## 🎯 Goals

- Learn different projection types in JPA and when to use each.
- Understand how to model real-world relationships (`@OneToMany`, `@ManyToOne`, `@OneToOne`, `@ManyToMany`).
- Practice keeping both database state and in-memory objects consistent.

---

## ✅ What I Implemented

### 1. Projections

- **Scalar Projection** → query a single column (`List<String>`).
- **DTO/Record Projection** → map results directly into a record or DTO (`new BookSummary(title, author)`).
- **Interface Projection** → JPA generates proxies for getters.
- **Nested Projection** → traverse relationships while shaping results into structured JSON.

### 2. Relationships

- **One-to-Many / Many-to-One** → Author ↔ Book with helper methods (`addBook`, `removeBook`).
- **One-to-One** → User ↔ Profile.
- **Many-to-Many** → Student ↔ Course with a join table (`student_course`).

---

## 📝 Key Takeaways

- Projections let you return **only the fields you need**, reducing overhead.
- Helper methods (`addBook`, `removeBook`) keep both sides of a relationship in sync.
- `orphanRemoval` automatically deletes children removed from a parent’s collection.
- Infinite recursion (stack overflow) can happen when serializing bidirectional relationships → fix with `@JsonIgnore`,
  `@JsonManagedReference/@JsonBackReference`, or DTOs.
- Many-to-Many requires a **join table**, defined with `@JoinTable`.

---

## 🔗 Follow the Journey

- LinkedIn: [Theo Olalekan](https://www.linkedin.com/in/theo-olalekan)
- GitHub: [Spring With Theo](https://github.com/your-repo-link)

---

#SpringWithTheo #NoPostNoPeace #Java #SpringBoot #SpringDataJPA #Projections #Relationships