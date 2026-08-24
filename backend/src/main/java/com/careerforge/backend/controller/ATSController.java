package com.careerforge.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerforge.backend.dto.ATSRequest;
import com.careerforge.backend.dto.ATSResponse;
import com.careerforge.backend.service.ATSService;

@RestController
@RequestMapping("/api/ats")
public class ATSController {

    private final ATSService atsService;

    public ATSController(ATSService atsService) {
        this.atsService = atsService;
    }

    @PostMapping("/analyze")
    public ATSResponse analyze(@RequestBody ATSRequest request) {
        return atsService.analyze(request);
    }
}