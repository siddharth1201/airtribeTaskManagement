package com.siddharth.AirtribeTaskManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddharth.AirtribeTaskManagement.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
