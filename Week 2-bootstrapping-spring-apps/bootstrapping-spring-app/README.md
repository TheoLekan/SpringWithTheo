# 📝 Bootstrapping Overview – Spring With Theo Week 2

This week focused on understanding what happens when you start a Spring Boot application and how Spring wires things together based on the dependencies you include.

---

## 🔧 What is Bootstrapping in Spring Boot?

Bootstrapping refers to the process of:
- Setting up a Spring Boot application
- Auto-configuring components based on dependencies and configuration files
- Running the application using an embedded server

---

## 🔍 Key Concepts

### `@SpringBootApplication`
This annotation combines three powerful annotations:
- `@Configuration`: marks the class as a source of bean definitions
- `@ComponentScan`: tells Spring to scan the package and its subpackages
- `@EnableAutoConfiguration`: enables Spring Boot’s auto-configuration magic

---

### Auto-Configuration
Spring Boot checks the classpath and your `pom.xml` dependencies. Based on what's present, it tries to auto-configure sensible defaults.

✅ For example:
- If `spring-boot-starter-web` is in your `pom.xml`, it configures:
    - A `DispatcherServlet`
    - Embedded Tomcat
    - JSON handling with Jackson
    - Error handling controllers

---

### Starters
**Spring Boot Starters** are pre-defined dependency bundles that make setup easier.

Examples:
- `spring-boot-starter-web`: web development with MVC
- `spring-boot-starter-data-jpa`: JPA and Hibernate support
- `spring-boot-starter-test`: test dependencies in one go

Including a starter is like saying: “Spring, give me the tools I need for this use case.”

---

### Configuration Files
Spring supports both:
- `application.properties`
- `application.yml`

These are used for:
- Setting server ports
- Defining data source configs
- Toggling features (e.g. enabling dev tools)

---

## 💡 Notes & Observations

- The more I understand Spring Boot’s auto-magic, the less intimidating it feels.
- Starters make your app’s intent clear just by reading `pom.xml`.
- You can still override everything — Spring Boot’s defaults are helpful, but not rigid.

---

## 🚀 Next Up
For Week 3, I’ll be diving into:
- Spring Web (Controllers, Request Mappings, Response handling)
- Building full REST APIs

Stay tuned — and remember to follow the challenge using  
**#SpringWithTheo** & **#NoPostNoPeace**.