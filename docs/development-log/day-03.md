# CareerForge AI — Day 3 Development Log

## Date

16 August 2026

## Objective

Connect CareerForge backend with MySQL and configure JPA/Hibernate.

## What I Learned

- MySQL database and database users
- Spring Data JPA
- Hibernate
- JDBC
- `application.properties`
- Environment variables
- `@Entity`
- `@Id`
- `@GeneratedValue`
- Basic Entity → Table mapping

## What I Built

- Installed MySQL 8.4
- Created `careerforge` database
- Configured `careerforge_app` database user
- Connected Spring Boot to MySQL
- Moved database password to `DB_PASSWORD` environment variable
- Created the `User` entity

```java
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
}