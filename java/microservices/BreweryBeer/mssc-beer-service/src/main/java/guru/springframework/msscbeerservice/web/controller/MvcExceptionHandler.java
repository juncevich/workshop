package guru.springframework.msscbeerservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException ex) {
        final var constraintViolations = ex.getConstraintViolations();
        var       errorsList           = new ArrayList<String>(constraintViolations.size());
        constraintViolations.stream()
                .map(Object::toString)
                .forEach(errorsList::add);
        return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);

    }
}
