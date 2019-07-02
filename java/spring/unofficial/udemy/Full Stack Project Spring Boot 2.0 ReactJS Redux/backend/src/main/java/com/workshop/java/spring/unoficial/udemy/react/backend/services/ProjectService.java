package com.workshop.java.spring.unoficial.udemy.react.backend.services;

import com.workshop.java.spring.unoficial.udemy.react.backend.domain.Backlog;
import com.workshop.java.spring.unoficial.udemy.react.backend.domain.Project;
import com.workshop.java.spring.unoficial.udemy.react.backend.exceptions.ProjectIdException;
import com.workshop.java.spring.unoficial.udemy.react.backend.repositories.BacklogRepository;
import com.workshop.java.spring.unoficial.udemy.react.backend.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    private final BacklogRepository backlogRepository;

    public ProjectService(ProjectRepository projectRepository, BacklogRepository backlogRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
    }

    public Project saveOrUpdateProject(Project project) {
        String identifier = project.getProjectIdentifier().toUpperCase();
        try {
            project.setProjectIdentifier(identifier);

            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(identifier);
            }

            if (project.getId() != null) {
                project.setBacklog(backlogRepository.findByProjectIdentifier(identifier));
            }
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + identifier + "' already exists");
        }
    }

    public Project findByProjectIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' does not exists");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if (project == null) {
            throw new ProjectIdException("Cannot Project with ID '" + "'. This project does not exists");
        }
        projectRepository.delete(project);
    }
}
