# Java Backend Projects

A collection of Java backend projects focused on REST APIs, Spring Boot, databases, testing, Docker, and backend development practices.

The projects are intended to demonstrate practical backend development skills and modern software engineering tools.

## Projects

### `user-crud-api`

A Spring Boot REST API for managing users.

The application demonstrates:

* REST API development with Spring Boot
* CRUD operations
* Layered architecture
* DTOs
* Request validation
* Exception handling
* Spring Data JPA
* PostgreSQL
* Unit and integration testing
* Docker

### API Endpoints

| Method | Endpoint      | Description        |
| ------ | ------------- | ------------------ |
| GET    | `/users`      | Retrieve all users |
| GET    | `/users/{id}` | Retrieve a user    |
| POST   | `/users`      | Create a user      |
| PUT    | `/users/{id}` | Update a user      |
| DELETE | `/users/{id}` | Delete a user      |

## Technologies

* Java 21
* Spring Boot
* Spring MVC
* Spring Data JPA
* PostgreSQL
* Maven
* JUnit
* Docker
* GitHub Actions

## Project Structure

The backend follows a layered structure:

```text
backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/backend/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── exception/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   └── test/
├── Dockerfile
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## Running the Application

From the `backend` directory:

```bash
./mvnw spring-boot:run
```

The application runs on port `8080`.

## Running Tests

```bash
./mvnw test
```

## Docker

The backend includes a Dockerfile for running the packaged Spring Boot application in a Java 21 container.

Generated build files and IDE-specific files are excluded from the repository.
