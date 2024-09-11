package com.springboot.app.taskmanagementap.Interface;

import com.springboot.app.taskmanagementap.Entities.Dto.TaskDto;


import java.util.List;

public interface TaskInterface {
    TaskDto createTask(TaskDto taskDto);
    TaskDto getTaskById (Long taskId);
    List<TaskDto> getAllTasks();
    TaskDto updateTask(Long taskId, TaskDto taskDto);
    void deleteTask(Long taskId);
}
