# Day 11 — CareerForge Log

## Concepts Learned

### 1. Business Logic
- Service layer handles application/business rules.
- Controller handles API communication.
- Repository handles database operations.

### 2. Conditional Validation
- `REQUIRED` experience → `minimumExperience` must be provided.
- `FRESHER` → minimum experience is not required.
- `PREFERRED` → experience is optional.

### 3. Exception Handling
- `throw` raises an exception.
- `@ExceptionHandler` handles exceptions.
- `@ControllerAdvice` provides global exception handling.

### 4. REST API
- `POST` is used to create resources.
- `@RequestBody` converts JSON into a Java object.
- `@Valid` activates DTO validation.
- `201 CREATED` represents successful resource creation.

### 5. DTO Mapping
- `JobRequest` → recruiter input.
- `Job` → database entity.
- `JobResponse` → API response.
- Entity → DTO mapping keeps database and API models separate.

## Implementation

### Job API
- Created `JobController`.
- Added `POST /api/jobs`.
- Connected Controller → Service → Repository.
- Added conditional experience validation.
- Added `JobResponse` mapping.
- Reused the existing `GlobalExceptionHandler`.

## Today's Progress

- Started the recruiter Job Posting backend flow.
- Built the basic structured Job API foundation.
