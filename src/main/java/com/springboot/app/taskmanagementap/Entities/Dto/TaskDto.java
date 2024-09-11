package com.springboot.app.taskmanagementap.Entities.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDto {
        private Long id;

        @NotEmpty
        @Length(min = 5, max = 20)
        private String title;

        @NotEmpty
        @Length(min = 1, max = 50)
        private String description;

        @NotEmpty
        @Length(min = 5, max = 20)
        private String status;

        @NotEmpty
        @Length(min = 1, max = 20)
        private String priority;

        @NotNull
        private LocalDate dueDate;


        public TaskDto() {
        }

        public TaskDto(String status, String description, String title, Long id, String priority, LocalDate dueDate) {
                this.status = status;
                this.description = description;
                this.title = title;
                this.id = id;
                this.priority = priority;
                this.dueDate = dueDate;
        }
}