## CareerForge AI — Day 8 Log
# Date

21 August 2026

# Concepts Learned
# 1. LLM
LLM = Large Language Model.
We don't train our own model for CareerForge.
We use an existing LLM through an API.
# 2. LLM API

Learned how:

CareerForge Spring Boot
        ↓
     LLM API
        ↓
    AI Model
        ↓
    AI Response
# 3. Gemini Model Selection
Chose Gemini for the initial AI implementation.
Free-tier usage is being kept limited.
We will make AI calls only when actually required.
# 4. API Key

Learned:

API keys must not be hardcoded.
The key is provided through an environment variable.
GOOGLE_API_KEY was required by the GenAI SDK in our setup.
# 5. Prompt Engineering

Learned how to give Gemini:

Role/instruction
Student data
Constraints
Expected output
# 6. Grounding

# Important concept:

MySQL
 ↓
Spring Boot
 ↓
Student data
 ↓
Prompt
 ↓
Gemini

Gemini doesn't directly access our database.

# 7. AI Suggestion vs Final Resume

AI generates a suggestion.

AI Suggestion
     ↓
Student reviews
     ↓
Student edits/accepts
     ↓
Final Resume

The AI should not silently overwrite the student's information.

Implementation Completed
# 1. Gemini SDK

Added the Google GenAI dependency to pom.xml.

# 2. Gemini API Configuration

Configured the API key through the terminal environment.

Verified:

GEMINI_API_KEY is configured

and resolved the SDK requirement for:

GOOGLE_API_KEY
# 3. AIService

Created:

AIService.java

with the Gemini Client.

# 4. AIController

Created the AI endpoint structure.

# 5. Gemini Connection Test

Successfully connected:

Spring Boot
   ↓
Google GenAI SDK
   ↓
Gemini
   ↓
Response

Received:

## Hello, CareerForge, it is a pleasure to connect with you today!

So the Gemini integration is working. ✅

# 6. AI Resume Summary

Created:

POST /api/ai/resume-summary/{profileId}

The AI now receives actual CareerForge data:

Career Goal
Education
Skills
Projects
Experience

and generates a personalized resume summary.

# 7. Database Verification

Verified the CGPA directly using SQL:

SELECT cgpa
FROM education
WHERE profile_id = 1;

Confirmed the value was:

8.5

This confirmed that the AI-generated:

CGPA: 8.5

came from the actual CareerForge database data.

# 8. Resume Summary Editing

Created:

ResumeSummaryRequest.java

Learned that the DTO carries the student's edited summary from the frontend to the backend.

Flow:

AI Summary
    ↓
Student edits
    ↓
ResumeSummaryRequest
    ↓
StudentProfileService
    ↓
MySQL
# 9. Save Endpoint

Added the resume-summary update functionality.

Our controller uses:

@RequestMapping("/api/profiles")

Therefore the correct endpoint is:

PUT /api/profiles/1/resume-summary

The earlier /api/profile/... gave 404 because the actual mapping is /api/profiles.

Today's Final Architecture
                    CareerForge
                         │
                         ↓
                  Student Profile
                         │
          ┌──────────────┼──────────────┐
          ↓              ↓              ↓
      Education       Skills        Projects
          ↓              ↓              ↓
      Experience    Certifications
          │
          └──────────────┬──────────────┘
                         ↓
                    AIService
                         ↓
                       Gemini
                         ↓
                AI Resume Summary
                         ↓
                  Student Reviews
                         ↓
                   Accept / Edit
                         ↓
             ResumeSummaryRequest
                         ↓
              StudentProfileService
                         ↓
                       MySQL