# CareerForge AI — Day 9 Development Log

## Date

25 August 2026

## Objective

Implement and test the first version of the CareerForge ATS system with deterministic skill matching and Gemini-powered improvement suggestions.

## What I Learned

- ATS skill matching
- Skill Catalog
- Regex-based skill matching
- Regex word boundaries
- Deterministic ATS scoring
- DTO separation between ATS and AI processing
- Constructor Dependency Injection between services
- Service-to-service communication
- Using an LLM for suggestions instead of letting it determine the ATS score
- Grounding LLM output using verified ATS results

## Why It Is Needed

The ATS needs to compare a student's resume skills against a job description and identify:

- Matched skills
- Missing skills
- ATS score

The deterministic ATS engine makes the score predictable and explainable.

Gemini is then used to generate personalized improvement suggestions based on the verified ATS results.

## What I Implemented

- Created and tested multiple users and linked student profiles.
- Added test skills for backend and frontend profiles.
- Implemented ATS analysis through `ATSService`.
- Used `SkillCatalog` for the list of skills checked by the ATS.
- Improved skill detection using Regex word boundaries.
- Added `totalSkills` to `ATSResponse`.
- Created `ATSSuggestionRequest`.
- Connected `ATSService` with `AIService` using constructor injection.
- Added Gemini-powered ATS suggestions.
- Added `suggestions` to `ATSResponse`.
- Successfully tested the complete ATS API.

