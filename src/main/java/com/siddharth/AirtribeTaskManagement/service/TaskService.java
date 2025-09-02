package com.siddharth.AirtribeTaskManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddharth.AirtribeTaskManagement.repository.TaskRepository;
import com.siddharth.AirtribeTaskManagement.entity.Task;
import com.siddharth.AirtribeTaskManagement.exceptions.TaskNotFoundException;

import java.util.List;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) throws TaskNotFoundException {
        return taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }

    public Task createTask(Task task) throws RuntimeException {
        try{
            return taskRepository.save(task);
        }
        catch (Exception e) {
            throw new RuntimeException("Error creating task: " + e.getMessage());
        }
    } 

    public Task updateTask(Task task, Long id) {
        return taskRepository.findById(id).map(existingTask -> {
            existingTask.setName(task.getName());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
            existingTask.setPriority(task.getPriority());
            existingTask.setDueDate(task.getDueDate());
            return taskRepository.save(existingTask);
        }).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}
