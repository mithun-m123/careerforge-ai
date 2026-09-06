package com.careerforge.backend.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.careerforge.backend.dto.ATSRequest;
import com.careerforge.backend.dto.ATSResponse;
import com.careerforge.backend.dto.ApplicationRequest;
import com.careerforge.backend.dto.ApplicationStatusRequest;
import com.careerforge.backend.dto.RecruiterApplicationResponse;
import com.careerforge.backend.dto.StudentApplicationResponse;
import com.careerforge.backend.entity.ATSResult;
import com.careerforge.backend.entity.Application;
import com.careerforge.backend.entity.ApplicationStatus;
import com.careerforge.backend.entity.Job;
import com.careerforge.backend.entity.StudentProfile;
import com.careerforge.backend.repository.ApplicationRepository;
import com.careerforge.backend.repository.JobRepository;
import com.careerforge.backend.repository.StudentProfileRepository;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private StudentProfileRepository profileRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ATSService atsService;

    @InjectMocks
    private ApplicationService applicationService;

    @Test
    void testApply() {

        // Arrange

        StudentProfile profile = new StudentProfile();

        Job job = new Job();
        job.setId(1L);

        ApplicationRequest request = new ApplicationRequest();
        request.setProfileId(1L);
        request.setJobId(1L);

        when(profileRepository.findById(1L))
                .thenReturn(Optional.of(profile));

        when(jobRepository.findById(1L))
                .thenReturn(Optional.of(job));

        ATSResponse atsResponse = new ATSResponse(
                80,
                true,
                List.of("Java", "MySQL"),
                List.<String>of(),
                List.of("Git"),
                List.<String>of(),
                "Good match for the student",
                "Good candidate match for the recruiter"
        );

        when(atsService.analyze(any(ATSRequest.class)))
                .thenReturn(atsResponse);

        when(applicationRepository.save(any(Application.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act

        StudentApplicationResponse result =
                applicationService.apply(request);

        // Assert

        assertEquals(1L, result.getJobId());
        assertEquals("APPLIED", result.getStatus());
        assertEquals(80, result.getAtsScore());
        assertTrue(result.isEligible());

        // Verify

verify(atsService, times(1))
        .analyze(any(ATSRequest.class));
        verify(applicationRepository).save(any(Application.class));
    }

    @Test
void testDuplicateApplication() {

    // Arrange

    StudentProfile profile = new StudentProfile();

    Job job = new Job();
    job.setId(1L);

    ApplicationRequest request = new ApplicationRequest();
    request.setProfileId(1L);
    request.setJobId(1L);

    Application existingApplication = new Application();

    when(profileRepository.findById(1L))
            .thenReturn(Optional.of(profile));

    when(jobRepository.findById(1L))
            .thenReturn(Optional.of(job));

    when(applicationRepository.findByProfileIdAndJobId(1L, 1L))
            .thenReturn(Optional.of(existingApplication));

    // Act + Assert

    IllegalArgumentException exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () -> applicationService.apply(request)
            );

    assertEquals(
            "You have already applied to this job",
            exception.getMessage()
    );
}
@Test
void testProfileNotFound() {

    // Arrange

    ApplicationRequest request = new ApplicationRequest();
    request.setProfileId(99L);
    request.setJobId(1L);

    when(profileRepository.findById(99L))
            .thenReturn(Optional.empty());

    // Act + Assert

    IllegalArgumentException exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () -> applicationService.apply(request)
            );

    assertEquals(
            "Profile not found: 99",
            exception.getMessage()
    );
}

@Test
void testJobNotFound() {

    // Arrange

    StudentProfile profile = new StudentProfile();

    ApplicationRequest request = new ApplicationRequest();
    request.setProfileId(1L);
    request.setJobId(99L);

    when(profileRepository.findById(1L))
            .thenReturn(Optional.of(profile));

    when(jobRepository.findById(99L))
            .thenReturn(Optional.empty());

    // Act + Assert

    IllegalArgumentException exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () -> applicationService.apply(request)
            );

    assertEquals(
            "Job not found: 99",
            exception.getMessage()
    );
}

// Test 1 — Update status successfully

@Test
void testUpdateStatus() {

    // Arrange

    StudentProfile profile = new StudentProfile();

    Job job = new Job();
    job.setId(1L);

    ATSResult atsResult = new ATSResult();
    atsResult.setScore(80);
    atsResult.setEligible(true);

    Application application = new Application();
    application.setProfile(profile);
    application.setJob(job);
    application.setStatus(ApplicationStatus.APPLIED);
    application.setAtsResult(atsResult);

    ApplicationStatusRequest request = new ApplicationStatusRequest();
    request.setStatus(ApplicationStatus.SHORTLISTED);

    when(applicationRepository.findById(1L))
            .thenReturn(Optional.of(application));

    when(applicationRepository.save(any(Application.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

    // Act

    RecruiterApplicationResponse result =
            applicationService.updateStatus(1L, request);

    // Assert

    assertEquals("SHORTLISTED", result.getStatus());
    assertEquals(1L, result.getJobId());
    assertEquals(80, result.getAtsScore());
    assertTrue(result.isEligible());

    // Verify

    verify(applicationRepository, times(1))
            .findById(1L);

    verify(applicationRepository, times(1))
            .save(any(Application.class));
}

// Test 2 — Application not found

@Test
void testUpdateStatusApplicationNotFound() {

    // Arrange

    ApplicationStatusRequest request =
            new ApplicationStatusRequest();

    request.setStatus(ApplicationStatus.SHORTLISTED);

    when(applicationRepository.findById(99L))
            .thenReturn(Optional.empty());

    // Act + Assert

    IllegalArgumentException exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () -> applicationService.updateStatus(99L, request)
            );

    assertEquals(
            "Application not found: 99",
            exception.getMessage()
    );

    // Verify

    verify(applicationRepository, times(1))
            .findById(99L);

    verify(applicationRepository, times(0))
            .save(any(Application.class));
}

// Test 3 — Get application successfully

@Test
void testGetApplication() {

    // Arrange

    StudentProfile profile = new StudentProfile();

    Job job = new Job();
    job.setId(1L);

    ATSResult atsResult = new ATSResult();
    atsResult.setScore(85);
    atsResult.setEligible(true);
    atsResult.setMatchedRequiredSkills(
            List.of("Java", "MySQL")
    );
    atsResult.setMissingRequiredSkills(
            List.<String>of()
    );
    atsResult.setMatchedPreferredSkills(
            List.of("Git")
    );
    atsResult.setMissingPreferredSkills(
            List.<String>of()
    );
    atsResult.setStudentSuggestions(
            "Good profile"
    );

    Application application = new Application();
    application.setProfile(profile);
    application.setJob(job);
    application.setStatus(ApplicationStatus.APPLIED);
    application.setAtsResult(atsResult);

    when(applicationRepository.findById(1L))
            .thenReturn(Optional.of(application));

    // Act

    StudentApplicationResponse result =
            applicationService.getApplication(1L);

    // Assert

    assertEquals(1L, result.getJobId());
    assertEquals("APPLIED", result.getStatus());
    assertEquals(85, result.getAtsScore());
    assertTrue(result.isEligible());

    assertEquals(
            List.of("Java", "MySQL"),
            result.getMatchedRequiredSkills()
    );

    assertEquals(
            List.of("Git"),
            result.getMatchedPreferredSkills()
    );

    assertEquals(
            "Good profile",
            result.getStudentSuggestions()
    );

    // Verify

    verify(applicationRepository, times(1))
            .findById(1L);
}
}