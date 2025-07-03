# 🌐 Spring Web Basics – Week 3 Mini Project

This project is part of Week 3 of the **#SpringWithTheo** journey.  
The goal: to explore how Spring Boot handles web requests using Spring MVC and to build a simple REST API from scratch.

---

## 📌 What This Project Covers

- `@RestController` and `@RequestMapping`
- Handling:
    - `GET`, `POST`, `DELETE` HTTP methods
    - `@PathVariable`, `@RequestParam`, and `@RequestBody`
- Returning responses via `ResponseEntity`
- Auto-serialization of Java objects into JSON
- Setting and reading custom values from `application.properties`

---

## 🧱 API Endpoints

| Method | Path              | Description                |
|--------|-------------------|----------------------------|
| GET    | `/api/users`      | Returns a list of users    |
| GET    | `/api/users/{id}` | Returns a user by ID       |
| POST   | `/api/users`      | Creates a user (echo back) |
| DELETE | `/api/users/{id}` | Deletes a user (mocked)    |

---

## 📂 Folder Structure

```plaintext
spring-web-basics/
├── src/
│   └── main/java/com/springwiththeo/week3/spring_web_basics/
│       ├── UserApiApplication.java
│       ├── controller/
│       │   └── UserController.java
│       ├── model/
│       │   └── User.java
│       └── dto/
│           └── CreateUserRequest.java
├── application.properties
├── pom.xml
└── README.md
```

---

## 🚀 Next Steps

- Build and run the API
- Test endpoints with Postman or curl
- Experiment by adding new endpoints or custom error handling

---

## 🔗 Connect & Follow

Follow my progress and join the challenge:

- LinkedIn: [theoolalekan](https://www.linkedin.com/in/theoolalekan/)
- GitHub: [springwiththeo](https://github.com/TheoLekan/SpringWithTheo)

---

## 💸 Accountability Challenge

If I don’t post my update by **Sunday 11 PM GMT**, the first person to tag me with **#SpringWithTheo** and *
*#NoPostNoPeace** wins **€50**.

---

Thanks for following along. Let’s grow together — one commit at a time.
