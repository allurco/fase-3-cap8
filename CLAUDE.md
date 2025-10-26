# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Composador Carbono is a carbon compensation MVP API built with Spring Boot 3.5.7 and Java 25. The application provides RESTful services for tracking carbon emissions and compensations.

**Important**: The package name is `br.com.allur.composador_carbono` (with underscore), not `br.com.allur.composador-carbono` (with hyphen), due to Java package naming conventions.

## Development Commands

### Build and Run
```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run

# Package the application
./mvnw clean package
```

### Testing
```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=ComposadorCarbonoApplicationTests

# Run tests with coverage
./mvnw test jacoco:report
```

### Database

The application uses Oracle Database XE. Use Docker Compose to run the database locally:

```bash
# Start Oracle database
docker-compose up -d

# Stop Oracle database
docker-compose down

# View logs
docker-compose logs -f oracle-db
```

**Database Connection Details:**
- Host: localhost
- Port: 1521
- SID: ORCL
- Username: system
- Password: OraclePassword123
- SQL Developer Web: http://localhost:5500

The database requires about 2 minutes to fully start (see healthcheck configuration).

## Architecture

### Technology Stack
- Spring Boot 3.5.7
- Spring Data JPA for persistence
- Spring Web for REST APIs
- Jakarta Bean Validation for input validation
- Oracle JDBC (ojdbc11) for database connectivity

### Project Structure
- `src/main/java/br/com/allur/composador_carbono/` - Main application code
  - `models/` - JPA entity classes for domain model
  - `ComposadorCarbonoApplication.java` - Spring Boot application entry point
- `src/main/resources/application.properties` - Spring configuration
- `src/test/java/` - Test classes

### Domain Model

The application manages carbon emissions and compensation through the following entities:

- `Emissoes` - Carbon emissions tracking
- `Compensacoes` - Carbon compensation records
- `Fontes` - Emission sources
- `Projetos` - Compensation projects
- `Alertas` - System alerts

**Note**: Most model classes are currently skeletal and need implementation. Only `Emissoes` and `Alertas` have the `@Entity` annotation; other models need to be annotated for JPA persistence.

### Configuration

Database configuration is in `src/main/resources/application.properties`. The default configuration points to localhost Oracle database. For remote database (oracle.fiap.com.br), the commented connection string is available in the properties file.

## Development Notes

### Maven Wrapper
Use `./mvnw` (Unix/Mac) or `mvnw.cmd` (Windows) instead of requiring Maven to be installed globally.

### Database Schema
Initialization scripts can be placed in `./init-scripts/` directory, which is mounted to `/opt/oracle/scripts/startup` in the Oracle container.