package com.example.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.taskmanager.model.TaskEntity;
import com.example.taskmanager.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public TaskEntity createTask(TaskEntity taskEntity){
        return taskRepository.save(taskEntity);
    }

    public List<TaskEntity> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<TaskEntity> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public TaskEntity updateTask(Long id, TaskEntity updatedTask){
        Optional<TaskEntity> optionalTask = taskRepository.findById(id);

        if(optionalTask.isPresent()){
            TaskEntity existingTask=optionalTask.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            return taskRepository.save(existingTask);
        }
        else{
            throw new RuntimeException("Task not found with id :"+id);
        }
    }
}
