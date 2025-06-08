# JWT Authentication and Authorization with Spring Boot 3 and Spring Security 6

This repository provides a implementation of **JWT-based authentication and authorization** using **Spring Boot 3** and
**Spring Security 6**, following Clean Architecture.

## Overview

This project demonstrates:

- Secure login and role-based access control with JWT
- Clean separation of concerns through Clean Architecture
- Configurable environments and OpenAPI integration
- Easily extendable structure for real-world applications

## Tech Stack

| Category       | Technology                       |
|----------------|----------------------------------|
| Language       | Java 17                          |
| Frameworks     | Spring Boot 3, Spring Security 6 |
| Authentication | JWT (JSON Web Token), BCrypt     |
| Persistence    | JPA (Hibernate), H2 Database     |
| Build Tool     | Maven                            |
| Documentation  | Springdoc OpenAPI                |
| Monitoring     | Spring Boot Actuator             |

## Features

- ✅ User Registration and Login APIs
- ✅ JWT Generation and Validation
- ✅ Role-Based Access Control (RBAC)
- ✅ Secure password hashing using BCrypt
- ✅ CORS-ready structure (configuration pending)
- ✅ RESTful API design
- ✅ Interactive API Docs via OpenAPI (`/swagger-ui.html`)
- ✅ Application monitoring via Actuator (`/actuator`)

## Project Structure

This project adopts **Clean Architecture** to achieve high maintainability and scalability.

```
├── http/
├── src/
│   ├── main/
│   │   ├── java
│   │   │   └── com/marykuo/security/
│   │   │       ├── adapter/
│   │   │       │   ├── in/
│   │   │       │   │   ├── api/
│   │   │       │   │   │   ├── controller/
│   │   │       │   │   │   ├── exception/
│   │   │       │   │   │   ├── filter/
│   │   │       │   │   │   └── response/
│   │   │       │   │   ├── queue/
│   │   │       │   │   └── scheduler/
│   │   │       │   ├── out/
│   │   │       │   │   ├── database/
│   │   │       │   │   ├── queue/
│   │   │       │   │   └── cache/
│   │   │       ├── config/
│   │   │       ├── domain/
│   │   │       ├── service/
│   │   │       └── util/
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-local-h2.properties (default)
│   │       ├── application-local-postgres.properties
│   │       ├── application-prod.properties
│   │       ├── application-uat.properties
│   │       ├── logback-spring.xml
│   │       ├── monitor.properties
│   │       └── openapi.properties
│   └── test/
├── .gitignore
├── Dockerfile
├── pom.xml
└── README.md
```

## Quick Start

### Maven

Ensure you have **Java 17** and **Maven** installed. You can run the application using the following commands:

```bash
# Clone the repository
git clone https://github.com/marykuo/springboot3-springsecurity6-jwt.git
cd springboot3-springsecurity6-jwt

# Run the application (H2 profile by default)
mvn spring-boot:run
```

- API documentation: http://localhost:8080/swagger-ui.html
- Monitoring endpoint: http://localhost:8080/actuator

You may also refer to the included **HTTP request test files** to explore endpoints.

### Docker

This project includes a Dockerfile for easy deployment. Ensure you have **Docker** installed, then follow these steps:

1. Build the Docker image:
   ```
   docker build -t springboot3-springsecurity6-jwt -f Dockerfile .
   ```
2. Run the Docker container:
   ```
   docker run -d -p 8080:8080 springboot3-springsecurity6-jwt
   ```

## Configuration Notes

- To disable storing plain-text passwords, set active profile to `prod`, or update the `PasswordEncoder` bean in
  `SecurityConfiguration.java`.
- For OpenAPI config, update `openapi.properties`. To disable OpenAPI, set:
  ```properties
  springdoc.api-docs.enabled=false
  ```
- Profiles included:
    - local-h2 (default)
    - local-postgres
    - uat
    - prod

## TODO

- [ ] Add refresh token mechanism
- [ ] Add additional test coverage
- [ ] Document logging and monitoring strategy
