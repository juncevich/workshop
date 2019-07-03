package com.workshop.java.spring.unoficial.udemy.react.backend.services;

import com.workshop.java.spring.unoficial.udemy.react.backend.domain.Backlog;
import com.workshop.java.spring.unoficial.udemy.react.backend.domain.ProjectTask;
import com.workshop.java.spring.unoficial.udemy.react.backend.repositories.BacklogRepository;
import com.workshop.java.spring.unoficial.udemy.react.backend.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        projectTask.setBacklog(backlog);
        Integer ptSequence = backlog.getPtSequence();
        ptSequence++;

        backlog.setPtSequence(ptSequence);
        projectTask.setProjectSequence(projectIdentifier + "-" + ptSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        if (projectTask.getStatus() == null || projectTask.getStatus().equals("")) {
            projectTask.setStatus("TO_DO");
        }
        if (projectTask.getPriority() == null) {
            projectTask.setPriority(3);
        }


        return projectTaskRepository.save(projectTask);
    }
}
