# CareerForge AI — Day 6 Development Log

## Date

20 August 2026

## Objective

Build the foundation for CareerForge student profiles and connect a User with a Student Profile.

## What I Learned

- Repository Query Methods

- Service Layer Business Logic

- DTO Request and Response Flow

- JPA Entity Relationships

- `@OneToOne`

- `@JoinColumn`

- Foreign-Key Relationships

- User → StudentProfile Mapping

- Controller → Service → Repository Flow

## What I Built

- Moved `careerGoal` from `User` to `StudentProfile`

- Created `StudentProfile` Entity

- Created `StudentProfileRepository`

- Created `StudentProfileRequest` DTO

- Created `StudentProfileService`

- Created `StudentProfileController`

- Connected `User` and `StudentProfile` using `@OneToOne`

- Added `user_id` using `@JoinColumn`

- Created `POST /api/profiles`

- Connected an existing User with a Student Profile

- Successfully tested Student Profile creation using Postman

- Verified the User → StudentProfile relationship in MySQL