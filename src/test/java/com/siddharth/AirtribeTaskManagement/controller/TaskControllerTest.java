package com.siddharth.AirtribeTaskManagement.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.siddharth.AirtribeTaskManagement.entity.Task;
import com.siddharth.AirtribeTaskManagement.enums.Priority;
import com.siddharth.AirtribeTaskManagement.enums.TaskStatus;
import com.siddharth.AirtribeTaskManagement.service.TaskService;


@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private List<Task> taskList;

    @BeforeEach
    public void setUp() {
        // Initialize common test data before each test
        this.taskList = Arrays.asList(
            new Task("Task 1", "Description 1", TaskStatus.TODO, Priority.HIGH, null),
            new Task("Task 2", "Description 2", TaskStatus.IN_PROGRESS, Priority.MEDIUM, null)
        );
    }

    @Test
    public void testGetTasks() throws Exception {
        // 1. Arrange: Define the behavior of the mock TaskService.
        // When taskService.getAllTasks() is called, return our predefined list of tasks.
        when(taskService.getAllTasks()).thenReturn(taskList);

        // 2. Act & 3. Assert: Perform a mock HTTP GET request and assert the response.
        mockMvc.perform(get("/tasks") // Assuming your controller endpoint is "/api/tasks"
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk()) // Expect HTTP 200 OK status
               .andExpect(jsonPath("$", hasSize(2))) // Assert the root of the response JSON is an array of size 2
               .andExpect(jsonPath("$[0].name", is("Task 1"))) // Assert the title of the first task
               .andExpect(jsonPath("$[0].description", is("Description 1"))) // Assert the description
               .andExpect(jsonPath("$[1].name", is("Task 2"))); // Assert the title of the second task
    }
}