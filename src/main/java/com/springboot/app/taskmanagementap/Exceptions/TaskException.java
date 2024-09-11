package com.springboot.app.taskmanagementap.Exceptions;

import com.springboot.app.taskmanagementap.Exceptions.Answer.TaskAnswer;
import com.springboot.app.taskmanagementap.Exceptions.Usefull.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TaskException {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<TaskAnswer<?>> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        return TaskResponse.createErrorResponseEntity(
                "Entity not found",
                Arrays.asList(ex.getMessage()),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                request.getDescription(false)
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<TaskAnswer<?>> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return TaskResponse.createErrorResponseEntity(
                "Access denied",
                Arrays.asList(ex.getMessage()),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN,
                request.getDescription(false)
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<TaskAnswer<?>> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        return TaskResponse.createErrorResponseEntity(
                "Authentication failed",
                Arrays.asList(ex.getMessage()),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED,
                request.getDescription(false)
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<TaskAnswer<?>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return TaskResponse.createErrorResponseEntity(
                ex.getMessage(),
                Arrays.asList(ex.getMessage()),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                request.getDescription(false)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<TaskAnswer<?>> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        TaskAnswer<?> response = TaskResponse.createErrorResponse(
                "Validation error",
                errors,
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<TaskAnswer<?>> handleAllExceptions(Exception ex, WebRequest request) {
        TaskAnswer<?> response = TaskResponse.createErrorResponse(
                "An error occurred",
                Arrays.asList(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}