package com.siddharth.AirtribeTaskManagement.controller;

import org.springframework.web.bind.annotation.RestController;

import com.siddharth.AirtribeTaskManagement.dto.TaskRequestDTO;
import com.siddharth.AirtribeTaskManagement.dto.TaskResponseDTO;
import com.siddharth.AirtribeTaskManagement.entity.Task;
import com.siddharth.AirtribeTaskManagement.exceptions.TaskNotFoundException;
import com.siddharth.AirtribeTaskManagement.service.TaskService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// start with /tasks
import org.springframework.web.bind.annotation.RequestParam;


@RestController()
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    public TaskService taskService;
    
    @GetMapping("")
    public ResponseEntity<?> getTasks(@RequestParam(value = "id", required = false) String id) {
        if (id == null) {
            return ResponseEntity.ok(taskService.getAllTasks());
        }
        try {
            Long taskId = Long.parseLong(id);
            Task task = taskService.getTaskById(taskId); // Should throw TaskNotFoundException if not found
            return ResponseEntity.ok(TaskResponseDTO.fromEntity(task));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid id format");
        }
    }
    
    @PostMapping("")
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        Task task = new Task(
            taskRequestDTO.getName(),
            taskRequestDTO.getDescription(),
            taskRequestDTO.getStatus(),
            taskRequestDTO.getPriority(),
            taskRequestDTO.getDueDate()
        );

        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(201).body(TaskResponseDTO.fromEntity(createdTask));    
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable("id") Long id, @Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        Task task = new Task(
            taskRequestDTO.getName(),
            taskRequestDTO.getDescription(),
            taskRequestDTO.getStatus(),
            taskRequestDTO.getPriority(),
            taskRequestDTO.getDueDate()
        );
        Task updatedTask = taskService.updateTask(task, id);
        return ResponseEntity.ok(TaskResponseDTO.fromEntity(updatedTask));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        // return 204 with message "Task deleted successfully"
        return ResponseEntity.noContent().build();
    }
}
