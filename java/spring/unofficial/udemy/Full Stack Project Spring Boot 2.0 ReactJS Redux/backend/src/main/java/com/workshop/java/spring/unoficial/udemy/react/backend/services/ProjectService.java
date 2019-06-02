package com.workshop.java.spring.unoficial.udemy.react.backend.services;

import com.workshop.java.spring.unoficial.udemy.react.backend.domain.Project;
import com.workshop.java.spring.unoficial.udemy.react.backend.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {
        return projectRepository.save(project);
    }
}
