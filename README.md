# Composador Carbono

MVP API for carbon compensation tracking and management.

## Overview

Composador Carbono is a Spring Boot REST API designed to track carbon emissions and manage compensation projects. The application provides endpoints for managing emissions sources, compensation initiatives, and automated alerts.

## Tech Stack

- **Java 25**
- **Spring Boot 3.5.7**
  - Spring Web (REST APIs)
  - Spring Data JPA (Data persistence)
  - Jakarta Bean Validation
- **Oracle Database XE**
- **Maven** (Build tool)

## Prerequisites

- Java 25 or higher
- Docker and Docker Compose (for local database)
- Maven (or use the included Maven wrapper)

## Getting Started

### 1. Start the Database

The project uses Oracle Database XE running in Docker:

```bash
docker-compose up -d
```

The database will be available after ~2 minutes on:
- **JDBC**: `localhost:1521`
- **SQL Developer Web**: http://localhost:5500

**Default Credentials:**
- Username: `system`
- Password: `OraclePassword123`

### 2. Build the Project

```bash
./mvnw clean install
```

### 3. Run the Application

```bash
./mvnw spring-boot:run
```

The API will start on the default Spring Boot port (8080).

## Development

### Running Tests

```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=ComposadorCarbonoApplicationTests
```

### Building for Production

```bash
./mvnw clean package
```

The executable JAR will be created in the `target/` directory.

## Project Structure

```
composador-carbono/
├── src/
│   ├── main/
│   │   ├── java/br/com/allur/composador_carbono/
│   │   │   ├── models/          # Domain entities
│   │   │   └── ComposadorCarbonoApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
├── docker-compose.yml           # Oracle DB setup
├── pom.xml                      # Maven configuration
└── mvnw                         # Maven wrapper
```

## Domain Model

- **Emissoes** - Carbon emissions tracking
- **Compensacoes** - Compensation records
- **Fontes** - Emission sources
- **Projetos** - Compensation projects
- **Alertas** - System alerts

## Configuration

Application configuration is managed in `src/main/resources/application.properties`.

To connect to a remote Oracle database, update the datasource URL:
```properties
spring.datasource.url=jdbc:oracle:thin:@<host>:<port>:<SID>
spring.datasource.username=<username>
spring.datasource.password=<password>
```

## Database Management

### Stop the Database
```bash
docker-compose down
```

### View Database Logs
```bash
docker-compose logs -f oracle-db
```

### Custom Initialization Scripts
Place SQL scripts in `./init-scripts/` to have them automatically executed on container startup.

## License

This project is part of an MVP development initiative.

## Notes

- The package name uses underscore (`composador_carbono`) instead of hyphen due to Java naming conventions
- Database data is persisted in a Docker volume (`oracle_data`)
- Initial database startup may take up to 2 minutes