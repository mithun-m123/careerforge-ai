package com.careerforge.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.SkillRequest;
import com.careerforge.backend.dto.SkillResponse;
import com.careerforge.backend.entity.Skill;
import com.careerforge.backend.entity.StudentProfile;
import com.careerforge.backend.repository.SkillRepository;
import com.careerforge.backend.repository.StudentProfileRepository;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final StudentProfileRepository studentProfileRepository;

    public SkillService(
            SkillRepository skillRepository,
            StudentProfileRepository studentProfileRepository) {

        this.skillRepository = skillRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    public SkillResponse createSkill(SkillRequest request) {

        StudentProfile profile = studentProfileRepository
                .findById(request.getProfileId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        Skill skill = new Skill();

        skill.setName(request.getName());
        skill.setLevel(request.getLevel());
        skill.setProfile(profile);

        Skill savedSkill = skillRepository.save(skill);

        return new SkillResponse(
                savedSkill.getId(),
                savedSkill.getName(),
                savedSkill.getLevel()
        );
    }

    public List<SkillResponse> getSkillsByProfileId(Long profileId) {

        return skillRepository.findByProfileId(profileId)
                .stream()
                .map(skill -> new SkillResponse(
                        skill.getId(),
                        skill.getName(),
                        skill.getLevel()
                ))
                .toList();
    }

    public SkillResponse updateSkill(Long skillId, SkillRequest request) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        skill.setName(request.getName());
        skill.setLevel(request.getLevel());

        Skill updatedSkill = skillRepository.save(skill);

        return new SkillResponse(
                updatedSkill.getId(),
                updatedSkill.getName(),
                updatedSkill.getLevel()
        );
    }

    public void deleteSkill(Long skillId) {

        if (!skillRepository.existsById(skillId)) {
            throw new RuntimeException("Skill not found");
        }

        skillRepository.deleteById(skillId);
    }
}