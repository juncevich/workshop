package com.workshop.java.spring.unoficial.udemy.react.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectIdException extends RuntimeException {
    public ProjectIdException(String message) {
        super(message);
    }
}
