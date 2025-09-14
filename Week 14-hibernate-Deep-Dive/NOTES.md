# 🔄 Week 14 – JPA & Hibernate Annotations Deep Dive

This week was all about diving into **JPA/Hibernate annotations** — understanding how they map Java entities to relational database tables and how to fine-tune performance, integrity, and lifecycle.

---

## 🎯 Goal
Learn the core JPA/Hibernate annotations in depth:
- How entities map to tables and columns
- How relationships work (1:1, 1:N, N:M)
- How to manage cascade, fetch strategies, and lifecycle
- How to enforce constraints and optimize performance with Hibernate extras

---

## ✅ What I Learned

### **Day 1 – Basic Entity & Table Mapping**
- `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`
- Control over table names, column names, and generated SQL
- Example: `@Column(length = 50, nullable = false, unique = true)`

---

### **Day 2 – Relationships**
- `@OneToMany` ↔ `@ManyToOne`
- `@OneToOne` with owning vs inverse side
- `@ManyToMany` with `@JoinTable`
- Helper methods like `author.addBook(book)` to sync both sides

---

### **Day 3 – Cascade & Fetch**
- Cascade types:
    - `PERSIST`, `MERGE`, `REMOVE`, `REFRESH`, `DETACH`, `ALL`
- Fetch types:
    - `LAZY` (best practice default)
    - `EAGER` (only when absolutely needed)
- Cascade downwards (parent → child), not upwards

---

### **Day 4 – Orphan Removal & Lifecycle**
- `orphanRemoval = true` → delete children when detached
- Lifecycle callbacks:
    - `@PrePersist`, `@PreUpdate`, `@PostLoad`
- Audit superclass pattern:
    - `createdAt` + `updatedAt` fields managed automatically

---

### **Day 5 – Column & Table Constraints**
- `@Column` options:
    - `nullable`, `unique`, `length`, `precision`, `scale`, `columnDefinition`
- `@Table` options:
    - `uniqueConstraints` (multi-column uniqueness)
    - `indexes` (performance optimization)
    - `schema`
- Constraints = database rules (`NOT NULL`, `UNIQUE`, `CHECK`)

---

### **Day 6 – Hibernate Extras**
- `@BatchSize(size = X)` → solve N+1 problem with `IN (...)` batching
- `@DynamicInsert` & `@DynamicUpdate` → only changed fields included in SQL
- `@Cache` → Hibernate 2nd-level cache support
- `@Fetch(FetchMode.JOIN | SUBSELECT)` → fine control of collection loading

---

## 📝 Notes
- Use **constraints** to enforce integrity at the DB level.
- Keep **batch size reasonable** (10–50) for performance.
- Avoid `EAGER` fetch unless absolutely necessary.
- For production, don’t rely on `ddl-auto=update`; use migration tools (Flyway, Liquibase).

---

## 🔗 Follow the Journey
- LinkedIn: [Theo Olalekan](https://www.linkedin.com/in/theo-olalekan)
- GitHub: [Spring With Theo](https://github.com/TheoLekan/SpringWithTheo)

#SpringWithTheo #NoPostNoPeace #Java #SpringBoot #JPA #Hibernate #Annotations