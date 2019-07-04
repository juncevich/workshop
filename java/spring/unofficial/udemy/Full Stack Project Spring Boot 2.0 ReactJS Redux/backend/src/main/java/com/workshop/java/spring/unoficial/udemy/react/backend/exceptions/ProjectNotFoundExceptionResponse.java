package com.workshop.java.spring.unoficial.udemy.react.backend.exceptions;

import lombok.Data;

@Data
public class ProjectNotFoundExceptionResponse {
    private String projectNotFound;

    public ProjectNotFoundExceptionResponse(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }
}
