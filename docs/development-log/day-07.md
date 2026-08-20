# CareerForge AI — Day 7 Development Log
## Date

20 August 2026

## Objective

Build the core student profile modules and understand how related entities are connected using Spring Boot and JPA.

## What I Learned

- One-to-Many and Many-to-One relationships

- @ManyToOne

- @JoinColumn

- Entity relationships with foreign keys

- Request DTO and Response DTO separation

- Spring Data JPA derived query methods like findByProfileId()

- Service-layer business logic

- Entity-to-DTO conversion

- Reusable toResponse() methods

- Complete CRUD API structure

- POST, GET, PUT, and DELETE APIs

## What I Built

- Completed Education CRUD

- Added Skills CRUD

- Added Projects CRUD

- Added Experience CRUD

- Added Certifications CRUD

- Connected Education, Skills, Projects, Experience, and Certifications to StudentProfile

- Tested the APIs using Postman

## Problems Encountered

- Fixed StudentProfileRequest and StudentProfileResponse DTO issues

- Fixed Education profile relationship and missing setProfile() method

- Fixed StudentProfile duplicate-profile constraint issue

- Fixed missing Spring annotations such as @GetMapping and @PathVariable

## Testing

- Tested Education create and retrieve APIs

- Tested Skills CRUD

- Tested Project CRUD

- Tested Experience CRUD

- Tested Certification CRUD

- Verified data and relationships through MySQL and Postman

## Git

Day 7 changes are ready to commit and push.