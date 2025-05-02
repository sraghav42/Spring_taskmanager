package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.model.TaskEntity;
import com.example.taskmanager.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    @PostMapping
    public TaskEntity createTask(@RequestBody TaskEntity taskEntity){
        return taskService.createTask(taskEntity);
    }

    @GetMapping
    public List<TaskEntity> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskEntity getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id :"+id));
    }

    @PutMapping("/{id}")
    public TaskEntity updateTask(@PathVariable Long id, @RequestBody TaskEntity taskEntity){
        return taskService.updateTask(id, taskEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
