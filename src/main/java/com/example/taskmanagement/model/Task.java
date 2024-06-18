package com.example.taskmanagement.model;

import com.example.taskmanagement.enums.Priority;
import com.example.taskmanagement.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Task {
    String taskId;
    String title;
    String description;
    Date dueDate;
    Priority priority;
    Status status;
    User assignedBy;
    User assignedUser;
    boolean isCompleted;

    public Task(String title, String description, Date dueDate, Priority priority) {
        this.taskId = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = Status.PENDING;
    }
}
