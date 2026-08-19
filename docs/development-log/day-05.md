# CareerForge AI — Day 5 Development Log

## Date

19 August 2026

## Objective

Improve User CRUD APIs with proper status codes, validation, and error handling.

## What I Learned

- HTTP Status Codes
- `ResponseEntity`
- `Optional`
- Input Validation
- `@Valid`
- `@NotBlank`
- `@Email`
- `@ControllerAdvice`
- `@ExceptionHandler`
- Custom Exceptions

## What I Built

- Added proper `201 Created` response for user creation
- Added `204 No Content` for successful deletion
- Added `404 Not Found` handling for missing users
- Added user input validation
- Created `GlobalExceptionHandler`
- Created `UserNotFoundException`
- Added field-level validation error responses

## Problems Encountered

- Initial validation returned Spring's default error response
- Fixed global exception handling
- Fixed a brace-placement error in `GlobalExceptionHandler`
- Added proper handling for missing users

## Testing

- Tested valid and invalid user creation
- Tested existing and non-existing users
- Tested update and delete error handling
- Verified proper HTTP status codes using Postman

## Git

Day 5 changes are ready to commit and push.
