package ua.edu.chmnu.todoapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.chmnu.todoapi.model.Task;
import ua.edu.chmnu.todoapi.service.TaskService;
import ua.edu.chmnu.todoapi.dto.CreateTaskRequest;
import ua.edu.chmnu.todoapi.dto.UpdateTaskRequest;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.findAll();
    }

    @GetMapping({"{id}"})
    public Task getTask(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody CreateTaskRequest request) {
        return taskService.create(request);
    }

    @DeleteMapping({"{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        taskService.update(id, request);
    }
}
