package com.example.taskmanagement.filter;

import com.example.taskmanagement.enums.Priority;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskFilter {
    private Priority priority;
    private Date dueDate;
    private User assignedUser;

    public TaskFilter setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public TaskFilter setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public TaskFilter setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
        return this;
    }

    public List<Task> apply(List<Task> tasks){
        return tasks.stream()
                .filter(task -> (priority == null || task.getPriority() == priority))
                .filter(task -> (dueDate == null || task.getDueDate().equals(dueDate)))
                .filter(task -> (assignedUser == null || task.getAssignedUser().equals(assignedUser)))
                .collect(Collectors.toList());
    }
}
