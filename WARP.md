# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Development Commands

### Running the Application
```bash
# Run with Maven wrapper (recommended)
./mvnw spring-boot:run

# Run with global Maven
mvn spring-boot:run

# Run the built JAR
java -jar target/Todo-0.0.1-SNAPSHOT.jar
```

### Building and Testing
```bash
# Clean build
./mvnw clean package

# Run tests
./mvnw test

# Run tests with verification
./mvnw verify

# Run a specific test class
./mvnw test -Dtest=TodoApplicationTests

# Run with different profiles
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Database Access
- **H2 Console**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:file:./data/tareas`
- **Username**: `sa`
- **Password**: (empty)

## Architecture Overview

This is a Spring Boot REST API application following a standard 3-layer architecture:

### Core Components
- **TodoApplication.java**: Main Spring Boot application entry point with `@SpringBootApplication`
- **TodoController**: REST controller handling HTTP requests with CORS configured for `http://localhost:3000`
- **Todo**: JPA Entity with validation annotations (`@NotBlank`, `@Size`)
- **TodoRepository**: JpaRepository interface providing CRUD operations

### Data Layer
- **Database**: H2 embedded database with file persistence (`./data/tareas`)
- **ORM**: Spring Data JPA with Hibernate
- **Entity Validation**: Bean Validation with Jakarta annotations

### API Design
RESTful endpoints under `/api/todos`:
- `GET /api/todos` - List all todos
- `POST /api/todos` - Create new todo
- `PUT /api/todos/{id}/completar` - Toggle completion status
- `DELETE /api/todos/{id}` - Delete todo

### Key Dependencies
- Spring Boot 3.5.4 (Java 17+)
- Spring Data JPA
- Spring Web
- Spring Validation
- H2 Database
- Lombok (for reducing boilerplate)

### Configuration
- **Database**: File-based H2 database persisted in `./data/` directory
- **CORS**: Enabled for localhost:3000 frontend integration
- **Validation**: Automatic validation on `@RequestBody` with `@Valid`

## Development Notes

### Adding New Endpoints
When adding new endpoints to TodoController:
- Use `@Valid` annotation for request body validation
- Return appropriate `ResponseEntity` with HTTP status codes
- Handle `Optional` results from repository operations
- Include proper error responses (404, 400)

### Database Changes
Entity modifications require:
- JPA annotations on new fields
- Getter/setter methods (or Lombok annotations)
- Consider database migration strategy for production

### Testing API
The application runs on port 8080 by default. Use cURL examples from API_DOCS.md or test via H2 console for database verification.

### Package Structure
```
miguelldev.todo/
├── TodoApplication.java      # Main application
├── controller/
│   └── TodoController.java   # REST endpoints
├── model/
│   └── Todo.java            # JPA entity
└── repository/
    └── TodoRepository.java   # Data access layer
```
