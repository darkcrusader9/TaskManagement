package com.example.taskmanagement.service;

import com.example.taskmanagement.enums.Priority;
import com.example.taskmanagement.enums.Status;
import com.example.taskmanagement.filter.TaskFilter;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.specification.CompositeSpecification;

import java.util.*;
import java.util.stream.Collectors;

public class TaskManagementService {
    public TaskRepository taskRepository;
    Map<User, List<Task>> userToTasksMapping;
    NotificationService notificationService;

    public TaskManagementService(){
        taskRepository = new TaskRepository();
        userToTasksMapping = new HashMap<>();
        notificationService = new NotificationService();
    }

    public void createTask(String title, String description, Date dueDate, Priority priority){
        Task task = new Task(title, description, dueDate, priority);
        addTask(task);
    }

    public void addTask(Task task){
        taskRepository.addTask(task);
    }

    public void updateTask(Task task) {
        taskRepository.updateTask(task);
        updateInUserToTaskMapping(task);
    }

    private void updateInUserToTaskMapping(Task task) {
        User user = task.getAssignedUser();
        if (user != null) {
            List<Task> taskList = userToTasksMapping.getOrDefault(user, new ArrayList<>());
            taskList.removeIf(t -> t.getTaskId().equals(task.getTaskId()));
            taskList.add(task);
            userToTasksMapping.put(user, taskList);
        }
    }

    public void deleteTask(String taskId) {
        Task task = getTask(taskId);
        taskRepository.deleteTask(taskId);
        User user = task.getAssignedUser();
        if (user != null) {
            userToTasksMapping.getOrDefault(user, new ArrayList<>()).remove(task);
        }
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
        userToTasksMapping.putIfAbsent(user, new ArrayList<>());
        userToTasksMapping.get(user).add(task);
    }

    public void markTaskAsCompleted(String taskId) {
        Task task = taskRepository.getTask(taskId);
        if (task != null) {
            task.setStatus(Status.COMPLETED);
            taskRepository.updateTask(task);
            updateInUserToTaskMapping(task);
        }
    }

    public List<Task> filterTasks(CompositeSpecification compositeSpecification) {
        //return filter.apply();
        return taskRepository.getAllTasks().stream()
                .filter(compositeSpecification::isSatisfiedBy)
                .collect(Collectors.toList());
    }

    public List<Task> getTasksHistory(User user){
        return userToTasksMapping.getOrDefault(user, new ArrayList<>());
    }

    public void notify(Task task){
        notificationService.scheduleReminder(task);
    }
}
