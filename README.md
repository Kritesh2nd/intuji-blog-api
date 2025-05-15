# 📝 Blog API Project

This is a RESTful Blog API built using **Spring Boot**, following a clean and modular **layered architecture** (Controller → Service → Repository). It allows users to create, read, update, and list blog posts via API endpoints.

---

## ⚙️ Features and Technologies

- **Layered Architecture**  
  Separation of concerns with Controller, Service, and Repository layers.

- **MySQL Database**  
  Integrated with **MySQL** using **Spring Data JPA**, enabling easy and efficient database interaction.

- **Lombok**  
  Reduces boilerplate by auto-generating constructors, getters, setters, and more.

- **Spring Boot Validation**  
  Ensures input data integrity with field-level validation annotations such as `@NotBlank`.

- **Global Exception Handling**  
  Gracefully handles errors and provides user-friendly messages through a centralized exception handler.

- **Logging**  
  Logs all critical steps and actions using **Logger**, improving traceability and debugging.

- **Unit Testing**  
  The **Service layer** is tested with **JUnit 5** and **Mockito**, ensuring business logic correctness.

---

## 🛠️ Setup Instructions

### Prerequisites

- Java 24
- Maven
- MySQL
- IDE IntelliJ
- Postman for testing

### Clone the Project

```bash
git clone https://github.com/Kritesh2nd/intuji-blog-api
cd intuji-blog-api
```