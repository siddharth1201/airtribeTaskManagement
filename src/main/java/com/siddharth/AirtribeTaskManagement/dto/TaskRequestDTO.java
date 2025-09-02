package com.siddharth.AirtribeTaskManagement.dto;

import com.siddharth.AirtribeTaskManagement.enums.TaskStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.siddharth.AirtribeTaskManagement.enums.Priority;

public class TaskRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Length(max = 500, min = 20, message = "Description should be between 20 and 500 characters")
    private String description;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    @NotNull(message = "Priority is required")
    private Priority priority;

    // date value should be after current date
    @Future(message = "Due date must be in the future")
    @NotNull(message = "Due date is required")  
    private Date dueDate;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public TaskRequestDTO() {
    }

    public TaskRequestDTO(String name, String description, TaskStatus status, Priority priority, Date dueDate) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }


}
