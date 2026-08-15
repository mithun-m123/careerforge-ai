package com.careerforge.backend.service;

import org.springframework.stereotype.Service;
import com.careerforge.backend.dto.HealthResponse;

@Service
public class HealthService {

    public HealthResponse getHealthMessage() {
    return new HealthResponse(
        "UP",
        "CareerForge backend is running"
    );
}
}