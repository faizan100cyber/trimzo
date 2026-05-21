# Sprint 0 — Project Setup Report

**Date:** 11-05-2026
**Developer:** Mohd Faizan
**Goal:** Ready the Development environment

---

## ✅ Completed Tasks

### 1. Tools Installed & Verified
| Tool          | Version | Purpose                |
|---------------|---------|------------------------|
| JDK           | 21      | For running Java codes |
| PostgreSQL    | 15      | Database               |
| IntelliJ IDEA | Latest  | Code editor            |
| Git           | 2.54.0  | Version control        |
| Maven         | 3.9.15  | Build tool             |
| Postman       | Latest  | API testing            |

### 2. Database Setup
- Database Created: `trimzo_db`
- User Created: `trimzo_user`
- Permissions Given: `GRANT ALL PRIVILEGES`

### 3. Spring Boot Project Generated
- Framework: Spring Boot 3.5.14
- Language: Java 21
- Build Tool: Maven
- Package: `com.trimzo`

### 4. Dependencies Added
| Dependency        | Work                         |
|-------------------|------------------------------|
| Spring Web        | Handling HTTP requests       |
| Spring Data JPA   | Communicate with Database    |
| Spring Security   | Authentication/Authorization |
| Flyway            | Database migrations          |
| PostgreSQL Driver | DB connection                |
| Lombok            | Boilerplate code reduce      |
| JWT               | Token based authentication   |
| GeoIP2            | Country/City detection       |
| UA-Parser         | Device detection             |
| Commons Validator | URL validation               |
| Swagger UI        | API documentation            |

### 5. Folder Structure Created 
com.trimzo
├── controller
├── service
├── repository
├── entity
├── dto (request/response)
├── exception
├── config
└── util

### 6. application.properties Configured
- Server port: 8080
- Database connection configured
- JWT settings configured
- Flyway settings configured

### 7. Git + GitHub Setup
- Repository: github.com/faizan100cyber/trimzo
- First commit: "feat: initial project setup"
- Second commit: "docs: add sprint 0 setup documentation"

### 8. GeoLite2 Database
- Downloaded from MaxMind 
- `src/main/resources/` kept here

---

## 📚 Learnings

- How to generate Spring Boot project 
- What and why maven Dependencies
- How to set up PostgreSQL setup 
- How to use Git version control
- Why Professional folder structure is important

---

## 🎯 Next Sprint

**Sprint 1 — Database Migrations**
- Create 4 tables via Flyway  
- users, URLs, clicks, api_keys

---

**Status: ✅ COMPLETE**