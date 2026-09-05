# Day 13 — CareerForge Log
## Date
05 September 2026

## Concepts Learned

### 1. Application as a Relationship Entity
- Understood why a job application needs its own entity instead of directly connecting StudentProfile and Job.
- An application represents the relationship between a student and a job.
- The application can store additional information such as status, application date, and ATS evaluation.

### 2. Many-to-Many Relationship with Additional Data
- Understood that StudentProfile and Job have a many-to-many relationship.
- Instead of using a direct `@ManyToMany`, an `Application` entity is used because the relationship contains its own data.
- This pattern is useful when a relationship has attributes of its own.

### 3. Application Status
- Created an `ApplicationStatus` enum.
- Supported statuses:
  - `APPLIED`
  - `SHORTLISTED`
  - `INTERVIEW`
  - `SELECTED`
  - `REJECTED`
- Stored the enum using `@Enumerated(EnumType.STRING)`.

### 4. Application and ATSResult Relationship
- Connected `Application` with `ATSResult` using `@OneToOne`.
- Each application can have one ATS evaluation.
- Used cascading and orphan removal so the ATS result belongs to the application lifecycle.

## Implementation

### 1. Application Entity
- Created the `Application` entity.
- Added relationships with `StudentProfile` and `Job`.
- Added application status and application timestamp.
- Added the one-to-one relationship with `ATSResult`.

### 2. Application Repository
- Created `ApplicationRepository`.
- Added a derived query to find an application using:
  - `profileId`
  - `jobId`
- This helps identify whether a student has already applied for a particular job.

### 3. Application DTOs
- Created request and response DTOs for the Application API.
- Response includes application information and ATS evaluation details.

### 4. Application Service
- Implemented application creation logic.
- Retrieved the student profile and job.
- Created the application with `APPLIED` status.
- Recorded the application date.
- Triggered ATS evaluation for the application.
- Connected the resulting `ATSResult` to the application.

### 5. Application Controller
- Created REST API endpoints for applications.
- Implemented the application creation endpoint.
- Used `@RequestBody` and `@Valid` where required.
- Returned the appropriate HTTP response.

### 6. Candidate Ranking
- Added ranking of applications for a particular job.
- Used a Spring Data JPA derived query to order applications by ATS score.
- Created the endpoint:
  - `GET /api/applications/job/{jobId}/ranked`
- Verified that candidates are returned according to their ATS score.

## Testing

- Created applications for CareerForge test candidates.
- Verified that applications were connected to the correct profile and job.
- Verified ATS results were generated for applications.
- Tested candidate ranking using ATS scores.
- Confirmed that the highest-scoring candidate appears first.

## Today's Progress

- Completed the core **Job Application module**.
- Connected Applications with Jobs, Student Profiles, and ATS Results.
- Added application status and application timestamps.
- Integrated ATS evaluation with the application flow.
- Added recruiter-side candidate ranking based on ATS score.

