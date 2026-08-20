package com.careerforge.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerforge.backend.dto.SkillRequest;
import com.careerforge.backend.dto.SkillResponse;
import com.careerforge.backend.service.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<SkillResponse> createSkill(
            @RequestBody SkillRequest request) {

        return ResponseEntity.ok(
                skillService.createSkill(request)
        );
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<SkillResponse>> getSkills(
            @PathVariable Long profileId) {

        return ResponseEntity.ok(
                skillService.getSkillsByProfileId(profileId)
        );
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<SkillResponse> updateSkill(
            @PathVariable Long skillId,
            @RequestBody SkillRequest request) {

        return ResponseEntity.ok(
                skillService.updateSkill(skillId, request)
        );
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteSkill(
            @PathVariable Long skillId) {

        skillService.deleteSkill(skillId);

        return ResponseEntity.noContent().build();
    }
}
