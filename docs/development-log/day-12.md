# Day 12 — CareerForge Log
## Date

04 September 2026

## Concepts Learned

### 1. Semantic Matching
- Understood the difference between exact matching, skill normalization, and semantic matching.
- Semantic matching compares the meaning of job and resume text rather than only matching exact words.
- Embeddings convert text into numerical vectors.
- Cosine similarity can measure how semantically similar two vectors are.
- Semantic matching should act as a supporting ATS signal and should not override hard requirements.

### 2. Hybrid ATS Matching
- CareerForge uses multiple matching layers:
  - Exact skill matching
  - Skill normalization
  - Semantic matching
- Hard requirements remain deterministic for eligibility.
- AI/semantic signals should not independently decide candidate eligibility.

## Implementation

### ATS Architecture Review
- Reviewed where semantic matching fits into the CareerForge ATS pipeline.
- Confirmed that Gemini generation and skill normalization alone do not constitute semantic matching.
- Identified embeddings + similarity calculation as the key evidence of semantic matching.

## Today's Progress

- Completed understanding of semantic matching.
- Reviewed the current ATS architecture and its matching layers.
- Prepared the project to move forward to the Job Application and Candidate Ranking module.

## Next

- Implement Job Application module.
- Connect applications with StudentProfile and Job.
- Store ATS score and eligibility for candidate ranking.
- Build recruiter-side candidate ranking flow.