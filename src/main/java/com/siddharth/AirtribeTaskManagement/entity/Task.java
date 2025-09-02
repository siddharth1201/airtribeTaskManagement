package com.siddharth.AirtribeTaskManagement.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;

import com.siddharth.AirtribeTaskManagement.enums.TaskStatus;
import com.siddharth.AirtribeTaskManagement.enums.Priority;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.AUTO;;

@Entity
public class Task {
    
    @Id
    @GeneratedValue(strategy = AUTO)
    public Long id;
 
    @NotBlank(message = "Name cannot be blank")
    public String name;

    @Length(max = 500, min=20, message = "Description should be between 20 and 500 characters")
    public String description;

    // SHould be an enum
    @Enumerated(EnumType.STRING)
    @NonNull
    public TaskStatus status;

    @Enumerated(EnumType.STRING)
    @NonNull
    public Priority priority;

    public Date createdAt;

    public Date updatedAt;


    public Date dueDate;

    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public Task() {
        // Default constructor
    }

    public Task(String name, String description, TaskStatus status, Priority priority, Date dueDate) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        // set current date for createdAt if not provided
        this.createdAt =  new Date();
        this.updatedAt =  new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public String getName() {
        return name;
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

    public Date getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Priority getPriority() {
        // TODO Auto-generated method stub
        return priority;
    }

    public void setPriority(Priority priority) {
        // TODO Auto-generated method stub
        this.priority = priority;
    }
}