CareerForge AI --- Day 4 Development Log

Date

18 August 2026

Phase

Phase 1 --- Foundations & Core Engineering

Objective

Learn the Spring Boot backend flow from Entity to REST API and implement
basic CRUD operations with MySQL.

What I Learned

Entity → Table mapping using JPA/Hibernate

JpaRepository and Repository abstraction

Spring Beans

Dependency Injection

Service Layer

Controller Layer

@RestController

@PostMapping

@GetMapping

@PutMapping

@DeleteMapping

@RequestBody

@PathVariable

CRUD operations

List<User>

Basic API testing using Postman

What I Built

Created UserRepository

Created UserService

Created UserController

Implemented Create User API

Implemented Get All Users API

Implemented Get User by ID API

Implemented Update User API

Implemented Delete User API

Added getters and setters to the User entity

Tested APIs using Postman

Verified data was stored in MySQL

Problems Encountered

User class was not recognized in UserService because the
import was missing.

Initial POST request returned {} because User had no
getters/setters.

First database record contained NULL name and email because
setters were missing.

Postman initially showed ECONNREFUSED because the Spring Boot
server was not running.

PUT request initially returned 405 Method Not Allowed because
the updated Controller code had not been loaded by the running
server.

Restarted the Spring Boot server and successfully verified the APIs.

Testing

Tested the following APIs using Postman:

POST   /api/users        ✅
GET    /api/users        ✅
GET    /api/users/{id}   ✅
PUT    /api/users/{id}   ✅
DELETE /api/users/{id}   ✅