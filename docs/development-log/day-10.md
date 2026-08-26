# Day 10 — CareerForge Log

## Concepts Learned

### 1. Keyword Matching vs Semantic Matching
- Current ATS uses exact/keyword-based skill matching.
- Semantic matching compares the meaning of text rather than only exact words.
- Example: `REST API development` and `RESTful web services` can have similar meaning.

### 2. Embeddings
- Embeddings convert text into numerical vectors.
- Similar meanings produce vectors that are closer together.
- Embeddings can be used for semantic matching.

### 3. Cosine Similarity
- Cosine similarity compares two embedding vectors.
- Higher similarity means the texts are more semantically related.
- Semantic similarity should be a supporting signal, not the entire ATS score.

### 4. LLM vs Embedding Model
- LLM → understands/generates language.
- Embedding model → converts text into vectors for similarity.
- API → communication method between our backend and the AI service.
- Gemini → the AI model/service we use through an API.

### 5. Hybrid ATS Architecture
- Exact skill matching provides reliable, deterministic matching.
- Semantic matching handles different wording and contextual similarity.
- Gemini is used for explanations and suggestions.
- Final scoring should remain deterministic and explainable.

### 6. Structured vs Unstructured Job Descriptions
- CareerForge recruiters will post jobs directly through our platform.
- We can collect structured information such as:
  - Job title
  - Description
  - Required skills
  - Preferred skills
  - Experience
  - Seniority
- Structured data can be processed directly without unnecessary LLM extraction.
- Future external/unstructured job sources can be converted into the same internal structure.

### 7. Required vs Preferred Skills
- Required skills are mandatory.
- Preferred skills are optional and recruiters can skip them.
- Candidates should not be penalized for preferred skills when the recruiter does not provide any.
- Required and preferred skills must be tracked separately.

### 8. Experience Requirements
Three recruiter options were decided:
- `FRESHER`
- `PREFERRED`
- `REQUIRED`

If experience is required/preferred, minimum experience can be specified.

### 9. Seniority
- Seniority is different from years of experience.
- Planned levels:
  - `INTERN`
  - `ENTRY_LEVEL`
  - `JUNIOR`
  - `MID_LEVEL`
  - `SENIOR`
  - `LEAD`

### 10. Eligibility vs ATS Score
- Eligibility determines whether mandatory requirements are satisfied.
- ATS score measures the overall match.
- A candidate can have a high score but still be ineligible because of a failed hard requirement.
- Semantic similarity cannot override a mandatory requirement.

### 11. Hard vs Soft Requirements
- Hard requirements → determine eligibility.
- Soft/preferred requirements → influence the score but do not automatically make the candidate ineligible.
- Missing unspecified requirements should not create a penalty.

### 12. Job ↔ Skill Database Design
Instead of storing skills as comma-separated text:

```text
Job
 ↓
JobSkill
 ↓
Skill