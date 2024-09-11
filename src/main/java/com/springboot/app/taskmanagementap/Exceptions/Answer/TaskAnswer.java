package com.springboot.app.taskmanagementap.Exceptions.Answer;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskAnswer<T> {
    private boolean success;
    private String message;
    private T data;
    private List<String> errors;
    private int errorCode;
    private long timestamp;
    private String path;
}