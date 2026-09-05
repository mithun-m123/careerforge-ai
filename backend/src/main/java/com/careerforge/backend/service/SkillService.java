package com.careerforge.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careerforge.backend.dto.SkillRequest;
import com.careerforge.backend.dto.SkillResponse;
import com.careerforge.backend.entity.ProfileSkill;
import com.careerforge.backend.entity.Skill;
import com.careerforge.backend.entity.StudentProfile;
import com.careerforge.backend.repository.ProfileSkillRepository;
import com.careerforge.backend.repository.SkillRepository;
import com.careerforge.backend.repository.StudentProfileRepository;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final ProfileSkillRepository profileSkillRepository;
    private final StudentProfileRepository studentProfileRepository;

    public SkillService(
            SkillRepository skillRepository,
            ProfileSkillRepository profileSkillRepository,
            StudentProfileRepository studentProfileRepository) {

        this.skillRepository = skillRepository;
        this.profileSkillRepository = profileSkillRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    public SkillResponse createSkill(SkillRequest request) {

        StudentProfile profile =
                studentProfileRepository.findById(request.getProfileId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student profile not found"));

        Skill skill =
                skillRepository.findByNameIgnoreCase(request.getName())
                        .orElseGet(() -> {
                            Skill newSkill = new Skill();
                            newSkill.setName(request.getName());
                            return skillRepository.save(newSkill);
                        });

        ProfileSkill profileSkill = new ProfileSkill();

        profileSkill.setProfile(profile);
        profileSkill.setSkill(skill);
        profileSkill.setLevel(request.getLevel());

        ProfileSkill saved =
                profileSkillRepository.save(profileSkill);

        return new SkillResponse(
                saved.getId(),
                saved.getSkill().getName(),
                saved.getLevel()
        );
    }

    public List<SkillResponse> getSkillsByProfileId(Long profileId) {

        return profileSkillRepository.findByProfileId(profileId)
                .stream()
                .map(profileSkill ->
                        new SkillResponse(
                                profileSkill.getId(),
                                profileSkill.getSkill().getName(),
                                profileSkill.getLevel()
                        ))
                .toList();
    }

    public SkillResponse updateSkill(
            Long profileSkillId,
            SkillRequest request) {

        ProfileSkill profileSkill =
                profileSkillRepository.findById(profileSkillId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Skill not found"));

        Skill skill =
                skillRepository.findByNameIgnoreCase(request.getName())
                        .orElseGet(() -> {
                            Skill newSkill = new Skill();
                            newSkill.setName(request.getName());
                            return skillRepository.save(newSkill);
                        });

        profileSkill.setSkill(skill);
        profileSkill.setLevel(request.getLevel());

        ProfileSkill updated =
                profileSkillRepository.save(profileSkill);

        return new SkillResponse(
                updated.getId(),
                updated.getSkill().getName(),
                updated.getLevel()
        );
    }

    public void deleteSkill(Long profileSkillId) {

        if (!profileSkillRepository.existsById(profileSkillId)) {
            throw new RuntimeException("Skill not found");
        }

        profileSkillRepository.deleteById(profileSkillId);
    }
}