package com.workshop.java.spring.unoficial.udemy.react.backend.web;

import com.workshop.java.spring.unoficial.udemy.react.backend.domain.ProjectTask;
import com.workshop.java.spring.unoficial.udemy.react.backend.services.MapValidationErrorService;
import com.workshop.java.spring.unoficial.udemy.react.backend.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationErrorService validationErrorService;

    @PostMapping("/{backlogId}")
    public ResponseEntity addPtToBacklog(@Valid @RequestBody ProjectTask projectTask,
                                            BindingResult bindResult, @PathVariable String backlogId) {
        Map<String, String> errorMap = validationErrorService.mapValidationService(bindResult);
        if (errorMap != null) {
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlogId, projectTask);
        return new ResponseEntity<>(projectTask1, HttpStatus.OK);

    }

    @GetMapping("/{backlogId}")
    public List<ProjectTask> getProjectBacklog(@PathVariable String backlogId) {
        return projectTaskService.findBacklogById(backlogId);
    }
}
