# Day 12 — CareerForge Log

## Date
04 September 2026
## Concepts Learned

### 1. Testing the Job API
- Understood why an API should be tested before building further features.
- Learned to verify the complete request → controller → service → repository → database flow.
- Used Postman to test the Job creation API.

### 2. API Validation & Error Handling
- Tested valid and invalid Job requests.
- Verified validation and business-rule errors.
- Confirmed that errors are handled through the global exception handler.

### 3. Recruiter Job & Skill Requirements
- Tested structured recruiter job creation.
- Verified required and preferred skills.
- Verified that JobSkill connects a Job with a Skill and stores the requirement type.

### 4. ATS Testing
- Tested ATS analysis using a stored Job instead of passing a raw job description.
- Verified:
  - ATS score
  - eligibility
  - matched required skills
  - missing required skills
  - matched preferred skills
  - missing preferred skills
  - AI suggestions

### 5. Experience-Based Eligibility
- Integrated candidate experience into ATS eligibility.
- Experience is calculated from the candidate's stored Experience records.
- Required experience can make a candidate ineligible.

### 6. Skill Normalization
- Added skill normalization to handle common variations such as:
  - `js` → `javascript`
  - `springboot` → `spring boot`
  - `reactjs` → `react`
  - `nodejs` → `node.js`
  - `ts` → `typescript`

### 7. Conditional AI Suggestions
- Gemini is called only when there are missing required or preferred skills.
- ATS facts and score remain controlled by the backend.
- Gemini provides improvement suggestions rather than deciding the ATS result.

## Implementation

### Job API Testing
- Created and tested structured recruiter Jobs through `POST /api/jobs`.
- Verified Job, Skill, and JobSkill data in MySQL.

### ATS Improvements
- Changed ATS input from a raw job description to a `jobId`.
- ATS now retrieves the structured Job and its requirements from the database.
- Added required/preferred skill separation.
- Added experience eligibility.
- Added skill normalization.
- Updated ATS response and Gemini suggestion DTOs.

### Testing
- Tested the ATS endpoint using Postman.
- Verified an example result with:
  - matched required skill: Java
  - missing required skill: MySQL
  - missing preferred skill: Git
  - score: 35
  - eligible: false
- Ran Maven tests successfully.

## Today's Progress

- Job posting API was verified.
- Structured Job → Skill relationships were tested.
- ATS was connected to structured recruiter Jobs.
- Experience-based eligibility was added.
- Skill normalization was added.
- ATS scoring and AI suggestion flow were verified.
- `mvn test` completed successfully.

## Next

- Continue with the Job Application module.
- Connect students with Jobs through Applications.
- Store application status and ATS results.
- Build the recruiter candidate-review/ranking flow.