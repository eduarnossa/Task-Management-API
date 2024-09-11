package com.springboot.app.taskmanagementap.Services;

import com.springboot.app.taskmanagementap.Entities.Dto.TaskDto;
import com.springboot.app.taskmanagementap.Entities.TaskApi;
import com.springboot.app.taskmanagementap.Mapper.TaskMapper;
import com.springboot.app.taskmanagementap.Repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskDto> listAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }

    public TaskDto getTaskById(Long id) {
        TaskApi task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + id));
        return TaskMapper.toDto(task);
    }

    public TaskDto createTask(TaskDto taskDto) {
        Optional<TaskApi> existingTaskOptional = taskRepository.findById(taskDto.getId());
        if (existingTaskOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("Task with ID %d already exists", taskDto.getId()));
        }
        TaskApi task = TaskMapper.toEntity(taskDto);
        TaskApi savedTask = taskRepository.save(task);
        return TaskMapper.toDto(savedTask);
    }

    public TaskDto updateTask(Long id, TaskDto taskDto) {
        TaskApi existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + id));

        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setStatus(taskDto.getStatus());
        existingTask.setPriority(taskDto.getPriority());
        existingTask.setDueDate(taskDto.getDueDate());

        TaskApi updatedTask = taskRepository.save(existingTask);
        return TaskMapper.toDto(updatedTask);
    }

    public void deleteTask(Long id) {
        TaskApi existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + id));

        taskRepository.delete(existingTask);
    }

    public List<TaskDto> searchTasks(String query) {
        List<TaskApi> tasks = taskRepository.findByTitleContainingOrDescriptionContaining(query, query);
        return tasks.stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TaskApi> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public List<TaskApi> getTasksByPriority(String priority) {
        return taskRepository.findByPriority(priority);
    }

    public Long countTasks() {
        return taskRepository.count();
    }
}