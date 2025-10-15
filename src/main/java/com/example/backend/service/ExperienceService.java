package com.example.backend.service;

import com.example.backend.model.Experience;
import com.example.backend.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public List<Experience> getAllExperiences() {
        return experienceRepository.findAllByOrderByStartDateDesc();
    }

    public Optional<Experience> getExperienceById(Long id) {
        return experienceRepository.findById(id);
    }

    public List<Experience> getCurrentExperiences() {
        return experienceRepository.findByIsCurrentTrue();
    }

    public Experience createExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    public Optional<Experience> updateExperience(Long id, Experience experienceDetails) {
        return experienceRepository.findById(id).map(experience -> {
            experience.setCompany(experienceDetails.getCompany());
            experience.setPosition(experienceDetails.getPosition());
            experience.setCompanyLogoUrl(experienceDetails.getCompanyLogoUrl());
            experience.setLocation(experienceDetails.getLocation());
            experience.setEmploymentType(experienceDetails.getEmploymentType());
            experience.setDescription(experienceDetails.getDescription());
            experience.setResponsibilities(experienceDetails.getResponsibilities());
            experience.setTechnologies(experienceDetails.getTechnologies());
            experience.setStartDate(experienceDetails.getStartDate());
            experience.setEndDate(experienceDetails.getEndDate());
            experience.setCurrent(experienceDetails.isCurrent());
            experience.setDisplayOrder(experienceDetails.getDisplayOrder());
            return experienceRepository.save(experience);
        });
    }

    public boolean deleteExperience(Long id) {
        return experienceRepository.findById(id).map(experience -> {
            experienceRepository.delete(experience);
            return true;
        }).orElse(false);
    }
}