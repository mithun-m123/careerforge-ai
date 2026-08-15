# CareerForge AI — Day 2 Development Log

## Date
15 August 2026

## Objective
Understand the core Spring concepts required for the CareerForge backend and improve the first REST API by introducing a service layer, Dependency Injection, a DTO, and a structured JSON response.

## What I Learned

- **IoC (Inversion of Control):** Spring takes responsibility for creating and managing application objects instead of application code controlling object creation directly.
- **Spring Container:** The component responsible for creating, configuring, and managing Spring Beans.
- **Spring Bean:** An object that is created and managed by the Spring Container.
- **Dependency:** An object or class required by another class to perform its work.
- **Dependency Injection:** Spring provides a required dependency to a class instead of that class manually creating the dependency.
- **Constructor Injection:** A form of Dependency Injection where a dependency is provided through the class constructor.
- **Service Layer:** A layer used to keep application/business logic separate from controller and HTTP handling.
- **`@Service`:** Marks a class as a Spring-managed service component.
- **`@RestController`:** Marks a class as a REST controller that handles HTTP requests and returns response data.
- **`@GetMapping`:** Maps an HTTP GET request path to a controller method.
- **JSON:** A structured format commonly used to exchange data between frontend and backend.
- **DTO (Data Transfer Object):** An object used to carry structured data between parts of an application.

## IoC — Inversion of Control

IoC means that control over creating and managing application objects is transferred from application code to the Spring framework.

### Without Spring

```java
HealthController controller = new HealthController();
```

The application code directly creates the object.

### With Spring

Spring takes responsibility for creating and managing the application object.

```text
Application
      ↓ control transferred
Spring Framework
      ↓ creates/manages
Spring Beans
```

The first arrow means control over object creation is transferred to Spring.

The second arrow means Spring creates and manages the objects.

## Spring Container

The Spring Container creates and manages Spring Beans.

When CareerForge starts:

```text
CareerForge starts
      ↓
Spring Boot creates/configures the application context
      ↓
Spring scans the application
      ↓
Spring discovers eligible components
      ↓
Spring creates/manages their objects
      ↓
Application becomes ready
```

- `↓ Spring Boot creates/configures` → Spring Boot prepares the application context/container.
- `↓ Spring scans` → Spring looks through the application packages for eligible components.
- `↓ discovers` → Spring identifies classes such as controllers and services.
- `↓ creates/manages` → Spring creates and manages the corresponding objects.
- `↓ becomes ready` → the application is ready to handle requests.

The Spring Container is not a folder that we manually create.

## Spring Bean

A Spring Bean is an object created and managed by the Spring Container.

In CareerForge:

```text
HealthService
      ↓ @Service
Spring discovers it
      ↓
Spring creates/manages the object
      ↓
HealthService Bean
```

- `↓ @Service` → the annotation tells Spring that the class is a service component.
- `↓ discovers it` → component scanning finds the class.
- `↓ creates/manages` → Spring creates and manages the object.
- `↓ HealthService Bean` → the managed object is a Spring Bean.

## Dependency

A dependency is an object that another class needs to perform its work.

For example:

```text
HealthController
      ↓ needs
HealthService
```

The arrow means `HealthController` depends on `HealthService`.

## Dependency Injection

Instead of the controller creating its dependency:

```java
private HealthService healthService = new HealthService();
```

Spring provides the dependency.

```text
Spring Container
      ↓ provides
HealthService Bean
      ↓ injected into
HealthController
```

- `↓ provides` → Spring makes the managed HealthService available.
- `↓ injected into` → Spring supplies that object to HealthController.

This reduces manual object creation and keeps the classes less tightly coupled.

## Constructor Injection

CareerForge uses constructor injection.

```java
private final HealthService healthService;

public HealthController(HealthService healthService) {
    this.healthService = healthService;
}
```

The constructor receives `HealthService`.

The simplified flow is:

```text
Spring Container
      ↓
HealthService Bean
      ↓
HealthController constructor
      ↓
healthService field
```

- `↓` → Spring supplies the managed HealthService object to the controller constructor.
- `↓` → the constructor assigns that object to the controller field.

## Service Layer

A service keeps application/business logic separate from HTTP/controller logic.

CareerForge now follows:

```text
HTTP request
      ↓
Controller
      ↓ calls
Service
      ↓ returns
Response data
```

- `↓` after HTTP request → the request reaches the controller.
- `↓ calls` → the controller calls a service method.
- `↓ returns` → the service produces the required application result.

## HealthService

Created:

`HealthService.java`

Location:

`com.careerforge.backend.service`

The class uses:

```java
@Service
public class HealthService
```

The service currently contains:

```java
public String getHealthMessage() {
    return "CareerForge backend is running";
}
```

The method was later changed to return a structured `HealthResponse` object.

## REST Controller

`@RestController` tells Spring that a class handles REST/web requests and that its methods can return response data.

CareerForge uses:

```java
@RestController
public class HealthController
```

The controller handles the health API.

## `@GetMapping`

The endpoint is:

```java
@GetMapping("/api/health")
```

This maps an HTTP GET request for `/api/health` to the `health()` method.

```text
GET /api/health
      ↓ @GetMapping
HealthController.health()
```

- `↓ @GetMapping` → the annotation connects the GET request and URL path to the controller method.

## JSON

JSON is a structured format commonly used to exchange data between a frontend and backend.

### Before

The endpoint returned:

```text
CareerForge backend is running
```

### After

The endpoint returns structured data:

```json
{
  "status": "UP",
  "message": "CareerForge backend is running"
}
```

The frontend can access the individual values separately.

## DTO — Data Transfer Object

A DTO is an object used to carry data between parts of an application.

Created:

`HealthResponse.java`

Location:

`com.careerforge.backend.dto`

The DTO contains:

```java
private String status;
private String message;
```

It represents the structured response sent by the health API.

## HealthResponse Constructor

The DTO uses a constructor:

```java
public HealthResponse(String status, String message) {
    this.status = status;
    this.message = message;
}
```

A constructor is used when creating an object and initializing its data.

Example:

```java
new HealthResponse(
    "UP",
    "CareerForge backend is running"
);
```

This creates a HealthResponse object containing the status and message.

## Final CareerForge API Flow

```text
GET /api/health
      ↓ @GetMapping
HealthController
      ↓ calls
HealthService
      ↓ returns
HealthResponse
      ↓ converted by Spring
JSON response
      ↓ sent to
Browser
```

### Arrow meanings

- `↓ @GetMapping` → maps the GET request and URL to the controller method.
- `↓ calls` → the controller calls `HealthService`.
- `↓ returns` → the service returns a `HealthResponse` object.
- `↓ converted by Spring` → Spring converts the Java object into JSON.
- `↓ sent to` → the JSON response is returned to the browser.

## Architecture Improvement

### Before

```text
GET /api/health
      ↓
HealthController
      ↓
String response
```

The controller directly produced the response message.

### After

```text
GET /api/health
      ↓ @GetMapping
HealthController
      ↓ calls
HealthService
      ↓ returns
HealthResponse DTO
      ↓ converted by Spring
JSON response
```

The new structure separates:

- HTTP request handling
- Application/service logic
- Response data representation

## What I Built

### 1. HealthService

Created the service package and `HealthService.java`.

### 2. Dependency Injection

Injected `HealthService` into `HealthController` using constructor injection.

### 3. DTO

Created the `dto` package and `HealthResponse.java`.

### 4. Structured API Response

Changed `/api/health` from returning a plain String to returning a structured JSON response.

### 5. Testing

Successfully tested:

`http://localhost:8080/api/health`

The browser displayed the structured JSON response.

## Day 2 Status

- [x] Learned IoC
- [x] Learned Spring Container
- [x] Learned Spring Beans
- [x] Learned Dependency Injection
- [x] Learned constructor injection when required by the project
- [x] Created HealthService
- [x] Created service layer
- [x] Understood `@RestController`
- [x] Understood `@GetMapping`
- [x] Learned JSON
- [x] Created HealthResponse DTO
- [x] Implemented structured JSON response
- [x] Tested `/api/health` successfully
