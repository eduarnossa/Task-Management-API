package com.springboot.app.taskmanagementap.Repository;

import com.springboot.app.taskmanagementap.Entities.TaskApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskApi, Long> {
    List<TaskApi> findByTitleContainingOrDescriptionContaining(String title, String description);
    List<TaskApi> findByStatus(String status);
    List<TaskApi> findByPriority(String priority);
}