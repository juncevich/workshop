package com.workshop.java.spring.unoficial.udemy.react.backend.services;

import com.workshop.java.spring.unoficial.udemy.react.backend.domain.Project;
import com.workshop.java.spring.unoficial.udemy.react.backend.exceptions.ProjectIdException;
import com.workshop.java.spring.unoficial.udemy.react.backend.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findByProjectIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' does not exists");
        }
        return project;
    }

    public Iterable<Project> findAll() {
        return projectRepository.findAll();
    }
}
