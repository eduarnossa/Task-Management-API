package com.springboot.app.taskmanagementap.Exceptions.Usefull;

import com.springboot.app.taskmanagementap.Exceptions.Answer.TaskAnswer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.List;

public class TaskResponse {

    public static <T> TaskAnswer<T> createSuccessResponse(String message, T data, String path) {
        return TaskAnswer.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .errors(null)
                .errorCode(0)
                .timestamp(Instant.now().toEpochMilli())
                .path(path)
                .build();
    }

    public static <T> TaskAnswer<T> createErrorResponse(String message, List<String> errors, int errorCode, String path) {
        return TaskAnswer.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .errors(errors)
                .errorCode(errorCode)
                .timestamp(Instant.now().toEpochMilli())
                .path(path)
                .build();
    }

    public static ResponseEntity<TaskAnswer<?>> createErrorResponseEntity(String message, List<String> errors, int errorCode, HttpStatus status, String path) {
        TaskAnswer<?> response = createErrorResponse(message, errors, errorCode, path);
        return new ResponseEntity<>(response, status);
    }
}