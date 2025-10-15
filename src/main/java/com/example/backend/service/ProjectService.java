package com.example.backend.service;

import com.example.backend.model.Project;
import com.example.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAllByOrderByDisplayOrderAsc();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public List<Project> getFeaturedProjects() {
        return projectRepository.findByFeaturedTrue();
    }

    public List<Project> getProjectsByCategory(String category) {
        return projectRepository.findByCategoryOrderByDisplayOrderAsc(category);
    }

    public List<Project> searchProjects(String keyword) {
        return projectRepository.searchProjects(keyword);
    }

    public List<Project> getProjectsByTechnology(String technology) {
        return projectRepository.findByTechnology(technology);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Optional<Project> updateProject(Long id, Project projectDetails) {
        return projectRepository.findById(id).map(project -> {
            project.setTitle(projectDetails.getTitle());
            project.setDescription(projectDetails.getDescription());
            project.setShortDescription(projectDetails.getShortDescription());
            project.setImageUrl(projectDetails.getImageUrl());
            project.setLiveDemoUrl(projectDetails.getLiveDemoUrl());
            project.setGithubUrl(projectDetails.getGithubUrl());
            project.setTechnologies(projectDetails.getTechnologies());
            project.setCategory(projectDetails.getCategory());
            project.setStatus(projectDetails.getStatus());
            project.setFeatured(projectDetails.isFeatured());
            project.setDisplayOrder(projectDetails.getDisplayOrder());
            return projectRepository.save(project);
        });
    }

    public boolean deleteProject(Long id) {
        return projectRepository.findById(id).map(project -> {
            projectRepository.delete(project);
            return true;
        }).orElse(false);
    }
}