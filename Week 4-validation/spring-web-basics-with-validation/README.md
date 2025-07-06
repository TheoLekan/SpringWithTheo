# 🛡️ Week 4 – Validation & Exception Handling

Welcome to Week 4 of the **#SpringWithTheo** challenge!  
This week, I focused on making my Spring Boot REST API **more robust** by adding data validation and global exception handling.

---

## 📌 What I Learned

✅ Using `@Valid` on DTOs to validate request bodies  
✅ Applying Bean Validation constraints like `@NotBlank`, `@Email`, and `@Size`  
✅ Building a global exception handler with `@ControllerAdvice`  
✅ Handling `MethodArgumentNotValidException` to show field errors clearly  
✅ Returning consistent, structured JSON error responses  
✅ Adding helpful timestamps and error messages  
✅ Using appropriate HTTP status codes (400 for validation, 404 for resource not found)  
✅ Improving developer and client experience with clear errors

---

## 🧱 API Endpoints

| Method | Path             | Description                |
|--------|------------------|----------------------------|
| GET    | `/api/users`     | Returns a list of users    |
| GET    | `/api/users/{id}`| Returns a user by ID       |
| POST   | `/api/users`     | Creates a user             |
| DELETE | `/api/users/{id}`| Deletes a user             |

---

## 📂 Folder Structure

```plaintext
week4-validation-exceptions/
├── src/
│   └── main/java/com/springwiththeo/week4/validation/
│       ├── ValidationApplication.java
│       ├── controller/
│       │   └── UserController.java
│       ├── model/
│       │   └── User.java
│       ├── dto/
│       │   └── UserRequest.java
│       ├── exception/
│       │   ├── GlobalExceptionHandler.java
│       │   └── UserNotFoundException.java
│       └── error/
│           └── ApiErrorResponse.java
├── application.properties
├── pom.xml
└── README.md
```

---

## 🚀 Next Steps

- Add even more advanced validations (e.g., cross-field)
- Experiment with custom exception classes
- Add tests for negative scenarios and error handling

---

## 🧠 Key Takeaway

> *“A great API is not just about happy paths — it’s about failing gracefully.”*

---

## 🔗 Connect & Follow

Follow my progress and join the challenge:

- LinkedIn: [theoolalekan](https://www.linkedin.com/in/theoolalekan/)
- GitHub: [spring-with-theo](https://github.com/TheoLekan/SpringWithTheo)

---

## 💸 Accountability Challenge

If I don’t post my update by **Sunday 11 PM GMT**, the first person to tag me with **#SpringWithTheo** and **#NoPostNoPeace** wins **€50**.

---

Thanks for following along — let’s keep building, one commit at a time. 💻🔥
