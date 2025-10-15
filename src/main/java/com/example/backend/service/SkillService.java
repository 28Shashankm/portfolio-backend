package com.example.backend.service;

import com.example.backend.model.Skill;
import com.example.backend.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAllByOrderByDisplayOrderAsc();
    }

    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    public List<Skill> getSkillsByCategory(String category) {
        return skillRepository.findByCategoryOrderByDisplayOrderAsc(category);
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Optional<Skill> updateSkill(Long id, Skill skillDetails) {
        return skillRepository.findById(id).map(skill -> {
            skill.setName(skillDetails.getName());
            skill.setCategory(skillDetails.getCategory());
            skill.setProficiencyLevel(skillDetails.getProficiencyLevel());
            skill.setIconUrl(skillDetails.getIconUrl());
            skill.setDisplayOrder(skillDetails.getDisplayOrder());
            skill.setYearsOfExperience(skillDetails.getYearsOfExperience());
            return skillRepository.save(skill);
        });
    }

    public boolean deleteSkill(Long id) {
        return skillRepository.findById(id).map(skill -> {
            skillRepository.delete(skill);
            return true;
        }).orElse(false);
    }
}