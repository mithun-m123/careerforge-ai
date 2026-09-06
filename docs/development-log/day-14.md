# Day 14 — CareerForge Log
## Date
06 September 2026


## Concepts Learned

### 1. AI Separation
- Separated ATS AI suggestions for students and recruiters.
- Student suggestions focus on improvement.
- Recruiter explanations focus on candidate-job matching.
- AI does not control the ATS score.

### 2. Application Status Management
- Implemented application status updates.
- Learned `PATCH` for partial updates.
- Supported statuses such as `APPLIED`, `SHORTLISTED`, and `INTERVIEW`.
- Added `ApplicationStatusRequest`.

### 3. Unit Testing
- Learned JUnit and Mockito.
- Learned `@Mock` and `@InjectMocks`.
- Learned Arrange → Act → Assert.
- Learned `assertEquals()`, `assertTrue()`, and `assertThrows()`.
- Learned Mockito `verify()`, `times(1)`, and `times(0)`.
- Tested `ApplicationService` success and failure scenarios.

### 4. Controller Testing
- Learned `@WebMvcTest`.
- Learned `@MockitoBean` for Spring Boot 4.
- Learned `MockMvc` for simulating HTTP requests.
- Tested application creation, duplicate application handling, and application retrieval.

### 5. Integration Testing
- Learned `@SpringBootTest`.
- Tested that the complete Spring application context loads successfully.

## Implementation

### Automated Testing
- Added `ApplicationServiceTest`.
- Added controller tests for `ApplicationController`.
- Added Spring application context integration test.
- Verified tests using Maven.

## Today's Progress

- Completed application status management.
- Completed AI student/recruiter separation.
- Completed core ApplicationService unit testing.
- Completed ApplicationController testing.
- Started integration testing.
- Learned practical JUnit, Mockito, MockMvc, and Spring Boot testing.
