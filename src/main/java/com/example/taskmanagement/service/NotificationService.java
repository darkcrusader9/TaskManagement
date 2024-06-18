package com.example.taskmanagement.service;

import com.example.taskmanagement.model.Task;

public class NotificationService {
    public void scheduleReminder(Task task) {
        System.out.println("Notification sent to " + task.getAssignedUser().getName() + ". Due date is " + task.getDueDate());
    }
}
