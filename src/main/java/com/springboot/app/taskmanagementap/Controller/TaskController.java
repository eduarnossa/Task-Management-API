package com.springboot.app.taskmanagementap.Controller;

import com.springboot.app.taskmanagementap.Entities.Dto.TaskDto;
import com.springboot.app.taskmanagementap.Entities.TaskApi;
import com.springboot.app.taskmanagementap.Exceptions.Answer.TaskAnswer;
import com.springboot.app.taskmanagementap.Exceptions.Usefull.TaskResponse;
import com.springboot.app.taskmanagementap.Services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<TaskAnswer<List<TaskDto>>> listAll() {
        try {
            List<TaskDto> tasks = taskService.listAll();
            TaskAnswer<List<TaskDto>> response = TaskResponse.createSuccessResponse(
                    "Tasks successfully recovered", tasks, "/tasks"
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            TaskAnswer<List<TaskDto>> response = TaskResponse.createErrorResponse(
                    "Failed to retrieve tasks",
                    List.of(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "/tasks"
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskAnswer<TaskDto>> getTaskById(@PathVariable Long id) {
        try {
            TaskDto task = taskService.getTaskById(id);
            TaskAnswer<TaskDto> response = TaskResponse.createSuccessResponse(
                    "Task successfully retrieved", task, "/tasks/" + id
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            TaskAnswer<TaskDto> response = TaskResponse.createErrorResponse(
                    "Task not found",
                    List.of(e.getMessage()),
                    HttpStatus.NOT_FOUND.value(),
                    "/tasks/" + id
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            TaskAnswer<TaskDto> response = TaskResponse.createErrorResponse(
                    "Failed to retrieve task",
                    List.of(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "/tasks/" + id
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<TaskAnswer<TaskDto>> createTask(@RequestBody TaskDto taskDto) {
        try {
            TaskDto createdTask = taskService.createTask(taskDto);
            TaskAnswer<TaskDto> response = TaskResponse.createSuccessResponse(
                    "Task successfully created", createdTask, "/tasks"
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            TaskAnswer<TaskDto> response = TaskResponse.createErrorResponse(
                    "Invalid task data",
                    List.of(e.getMessage()),
                    HttpStatus.BAD_REQUEST.value(),
                    "/tasks"
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            TaskAnswer<TaskDto> response = TaskResponse.createErrorResponse(
                    "Failed to create task",
                    List.of(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "/tasks"
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskAnswer<TaskDto>> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        try {
            TaskDto updatedTask = taskService.updateTask(id, taskDto);
            TaskAnswer<TaskDto> response = TaskResponse.createSuccessResponse(
                    "Task successfully updated", updatedTask, "/tasks/" + id
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            TaskAnswer<TaskDto> response = TaskResponse.createErrorResponse(
                    "Task not found",
                    List.of(e.getMessage()),
                    HttpStatus.NOT_FOUND.value(),
                    "/tasks/" + id
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            TaskAnswer<TaskDto> response = TaskResponse.createErrorResponse(
                    "Failed to update task",
                    List.of(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "/tasks/" + id
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskAnswer<Void>> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            TaskAnswer<Void> response = TaskResponse.createSuccessResponse(
                    "Task successfully deleted", null, "/tasks/" + id
            );
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            TaskAnswer<Void> response = TaskResponse.createErrorResponse(
                    "Task not found",
                    List.of(e.getMessage()),
                    HttpStatus.NOT_FOUND.value(),
                    "/tasks/" + id
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            TaskAnswer<Void> response = TaskResponse.createErrorResponse(
                    "Failed to delete task",
                    List.of(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "/tasks/" + id
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<TaskAnswer<List<TaskApi>>> getTasksByStatus(@PathVariable String status) {
        try {
            List<TaskApi> tasks = taskService.getTasksByStatus(status);
            TaskAnswer<List<TaskApi>> response = TaskResponse.createSuccessResponse(
                    "Tasks successfully retrieved by status", tasks, "/tasks/status/" + status
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            TaskAnswer<List<TaskApi>> response = TaskResponse.createErrorResponse(
                    "Failed to retrieve tasks by status",
                    List.of(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "/tasks/status/" + status
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<TaskAnswer<List<TaskApi>>> getTasksByPriority(@PathVariable String priority) {
        try {
            List<TaskApi> tasks = taskService.getTasksByPriority(priority);
            TaskAnswer<List<TaskApi>> response = TaskResponse.createSuccessResponse(
                    "Tasks successfully retrieved by priority", tasks, "/tasks/priority/" + priority
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            TaskAnswer<List<TaskApi>> response = TaskResponse.createErrorResponse(
                    "Failed to retrieve tasks by priority",
                    List.of(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "/tasks/priority/" + priority
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}