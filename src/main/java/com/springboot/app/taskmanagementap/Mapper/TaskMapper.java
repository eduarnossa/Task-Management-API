package com.springboot.app.taskmanagementap.Mapper;

import com.springboot.app.taskmanagementap.Entities.Dto.TaskDto;
import com.springboot.app.taskmanagementap.Entities.TaskApi;

public class TaskMapper {

    public static TaskDto toDto(TaskApi taskApi) {
        if (taskApi == null) {
            return null;
        }
        TaskDto taskDto = new TaskDto();
        taskDto.setId(taskApi.getId());
        taskDto.setTitle(taskApi.getTitle());
        taskDto.setDescription(taskApi.getDescription());
        taskDto.setStatus(taskApi.getStatus());
        taskDto.setPriority(taskApi.getPriority());
        taskDto.setDueDate(taskApi.getDueDate());
        return taskDto;
    }

    public static TaskApi toEntity(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }
        TaskApi taskApi = new TaskApi();
        taskApi.setId(taskDto.getId());
        taskApi.setTitle(taskDto.getTitle());
        taskApi.setDescription(taskDto.getDescription());
        taskApi.setStatus(taskDto.getStatus());
        taskApi.setPriority(taskDto.getPriority());
        taskApi.setDueDate(taskDto.getDueDate());
        return taskApi;
    }
}