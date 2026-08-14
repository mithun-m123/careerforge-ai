# CareerForge AI — Day 1 Development Log

## Date
14 August 2026

## Objective
Set up the CareerForge backend environment, initialize the Spring Boot project, understand the project structure, and build the first REST API.

## Environment

- Java 25.0.1
- Maven 3.9.16
- Git 2.54.0

## What I Learned

- Maven and its role as the Java project's build and dependency management tool.
- `pom.xml` and how Maven uses it to configure the project and dependencies.
- Spring Initializr and how it generates a Spring Boot project structure.
- Spring Boot project structure including `src/main`, `src/test`, Java packages, resources, and Maven Wrapper.
- `@SpringBootApplication` and the Spring Boot application entry point.
- HTTP requests, HTTP methods, endpoints, and controllers.
- `@RestController` and `@GetMapping`.

## What I Built

### CareerForge Backend

Initialized a Spring Boot backend using:

- Spring Boot 4.1.0
- Java 25
- Maven
- Spring Web

### Health Check API

Created:

`GET /api/health`

Response:

`CareerForge backend is running`

The endpoint was successfully tested through the browser.

## Project Structure

```text
careerforge-ai/
├── backend/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       └── test/
└── docs/
    └── development-log/
        └── day-01.md