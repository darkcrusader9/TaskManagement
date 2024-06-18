package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.Task;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ToString
public class TaskRepository {
    private Map<String, Task> taskMap = new ConcurrentHashMap<>();

    public void addTask(Task task) {
        taskMap.put(task.getTaskId(), task);
    }

    public void updateTask(Task task) {
        taskMap.put(task.getTaskId(), task);
    }

    public void deleteTask(String taskId) {
        taskMap.remove(taskId);
    }

    public Task getTask(String taskId) {
        return taskMap.get(taskId);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }
}
