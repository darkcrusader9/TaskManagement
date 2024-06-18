package com.example.taskmanagement.controller;

import com.example.taskmanagement.enums.Priority;
import com.example.taskmanagement.enums.Status;
import com.example.taskmanagement.filter.TaskFilter;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.repository.TaskRepository;

import java.util.Date;
import java.util.List;

public class TaskManagementServiceController {
    public TaskRepository taskRepository = new TaskRepository();

    public void createTask(String title, String description, Date dueDate, Priority priority){
        Task task = new Task(title, description, dueDate, priority);
        addTask(task);
    }

    public void addTask(Task task){
        taskRepository.addTask(task);
    }

    public void updateTask(Task task) {
        taskRepository.updateTask(task);
    }

    public void deleteTask(String taskId) {
        taskRepository.deleteTask(taskId);
    }

    public Task getTask(String taskId) {
        return taskRepository.getTask(taskId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public void assignTask(String taskId, User user){
        Task task = taskRepository.getTask(taskId);
        if (task != null) {
            task.setAssignedUser(user);
            taskRepository.updateTask(task);
        }
    }

    public void markTaskAsCompleted(String taskId) {
        Task task = taskRepository.getTask(taskId);
        if (task != null) {
            task.setStatus(Status.COMPLETED);
            taskRepository.updateTask(task);
        }
    }

    public List<Task> filterTasks(TaskFilter filter) {
        return filter.apply(taskRepository.getAllTasks());
    }
}
