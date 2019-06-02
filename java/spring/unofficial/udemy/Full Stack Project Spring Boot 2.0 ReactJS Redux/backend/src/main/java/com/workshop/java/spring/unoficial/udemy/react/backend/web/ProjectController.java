package com.workshop.java.spring.unoficial.udemy.react.backend.web;

import com.workshop.java.spring.unoficial.udemy.react.backend.domain.Project;
import com.workshop.java.spring.unoficial.udemy.react.backend.services.MapValidationErrorService;
import com.workshop.java.spring.unoficial.udemy.react.backend.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;
    private final MapValidationErrorService validationErrorService;

    public ProjectController(ProjectService projectService, MapValidationErrorService validationErrorService) {
        this.projectService = projectService;
        this.validationErrorService = validationErrorService;
    }

    @PostMapping("")
    public ResponseEntity createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        Map<String, String> errorMap = validationErrorService.mapValidationService(bindingResult);
        if (errorMap != null) {
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }

        Project savedProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findByProjectIdentifier(projectId.toUpperCase());
        return new ResponseEntity<>(project, HttpStatus.OK);
    }


}
