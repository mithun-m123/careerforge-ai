// Test 1 — Create application successfully

package com.careerforge.backend.controller;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.careerforge.backend.dto.ApplicationRequest;
import com.careerforge.backend.dto.StudentApplicationResponse;
import com.careerforge.backend.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ApplicationController.class)
class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;
private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private ApplicationService applicationService;

    @Test
    void testCreateApplication() throws Exception {

        // Arrange

        ApplicationRequest request = new ApplicationRequest();
        request.setProfileId(1L);
        request.setJobId(1L);

        StudentApplicationResponse response =
                new StudentApplicationResponse();

        response.setProfileId(1L);
        response.setJobId(1L);
        response.setStatus("APPLIED");
        response.setAtsScore(80);
        response.setEligible(true);

        when(applicationService.apply(any(ApplicationRequest.class)))
                .thenReturn(response);

        // Act + Assert

        mockMvc.perform(
                post("/api/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(request)
                        )
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.profileId").value(1))
        .andExpect(jsonPath("$.jobId").value(1))
        .andExpect(jsonPath("$.status").value("APPLIED"))
        .andExpect(jsonPath("$.atsScore").value(80))
        .andExpect(jsonPath("$.eligible").value(true));
    }

    // Test 2 — Duplicate application returns 400

@Test
void testDuplicateApplication() throws Exception {

    // Arrange

    ApplicationRequest request = new ApplicationRequest();
    request.setProfileId(1L);
    request.setJobId(1L);

    when(applicationService.apply(any(ApplicationRequest.class)))
            .thenThrow(
                    new IllegalArgumentException(
                            "You have already applied to this job"
                    )
            );

    // Act + Assert

    mockMvc.perform(
            post("/api/applications")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                            objectMapper.writeValueAsString(request)
                    )
    )
    .andExpect(status().isBadRequest());
}

// Test 3 — Get application successfully

@Test
void testGetApplication() throws Exception {

    // Arrange

    StudentApplicationResponse response =
            new StudentApplicationResponse();

    response.setProfileId(1L);
    response.setJobId(1L);
    response.setStatus("APPLIED");
    response.setAtsScore(85);
    response.setEligible(true);

    when(applicationService.getApplication(1L))
            .thenReturn(response);

    // Act + Assert

    mockMvc.perform(
            get("/api/applications/1")
    )
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.profileId").value(1))
    .andExpect(jsonPath("$.jobId").value(1))
    .andExpect(jsonPath("$.status").value("APPLIED"))
    .andExpect(jsonPath("$.atsScore").value(85))
    .andExpect(jsonPath("$.eligible").value(true));
}
}